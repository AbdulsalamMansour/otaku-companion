package com.example.abdulsalam.otakucompanion.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.abdulsalam.otakucompanion.AnimeClient;
import com.example.abdulsalam.otakucompanion.Model.Responses.ResponseByGenre;
import com.example.abdulsalam.otakucompanion.Model.Responses.ResponseForAnimeSearch;
import com.example.abdulsalam.otakucompanion.Model.Responses.ResponseForTop;
import com.example.abdulsalam.otakucompanion.Model.SharedClasses.Anime;
import com.example.abdulsalam.otakucompanion.Model.SharedClasses.AnimeDetailed;
import com.example.abdulsalam.otakucompanion.Model.SharedClasses.Result;
import com.example.abdulsalam.otakucompanion.R;
import com.example.abdulsalam.otakucompanion.UI.AnimeAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainDisplayFragment extends Fragment implements AnimeAdapter.OnClickHandler {

    private DatabaseReference myRef;
    private String userID;

    @BindView(R.id.rv_animes)
    RecyclerView recyclerView;
    @BindView(R.id.progressbar_load)
    ProgressBar progressBar;
    AnimeAdapter animeAdapter;
    private List<AnimeDetailed> favorites = new ArrayList<>();
    private Unbinder mUnbinder = null ;

    //initialize retrofit

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://api.jikan.moe")
            .addConverterFactory(GsonConverterFactory.create());


    Retrofit retrofit = builder.build();
    AnimeClient client = retrofit.create(AnimeClient.class);



    public MainDisplayFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_main_display, container, false);
        mUnbinder = ButterKnife.bind(this,rootView);



        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        animeAdapter = new AnimeAdapter(getActivity(),this);
        recyclerView.setAdapter(animeAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Bundle bundle = getArguments();

        if(bundle != null && bundle.containsKey(getString(R.string.position)) ) {
            int genre = bundle.getInt(getString(R.string.position));
            genre++;
            progressBar.setVisibility(View.VISIBLE);
            progressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);


            getAnimeByGenre(Integer.toString(genre));

        }else if (bundle != null && bundle.containsKey(getString(R.string.search_key)) ){

            searchForAnimeByName(bundle.getString(getString(R.string.search_key)));

        }else if (bundle != null && bundle.containsKey(getString(R.string.fav)) ){

            progressBar.setVisibility(View.VISIBLE);
            progressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);

            getFavoriteAnimes();



        }else{
            progressBar.setVisibility(View.VISIBLE);
            progressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);
            getTopAnimes();
        }


        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
    @Override
    public void onItemClick(Anime anime) {

        AnimeDetailsFragment animeDetailsFragment = new AnimeDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.anime_key),anime);

        animeDetailsFragment.setArguments(bundle);

        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container,animeDetailsFragment).addToBackStack(null).commit();

    }


    //-------------------------------- API communication methods --------------------------------

    public void getTopAnimes(){


        if(checkConnection(Objects.requireNonNull(getActivity()))) {

            Retrofit retrofit = builder.build();
            AnimeClient client = retrofit.create(AnimeClient.class);


            Call<ResponseForTop> call = client.topAnime();


            call.enqueue(new Callback<ResponseForTop>() {
                @Override
                public void onResponse(@NonNull Call<ResponseForTop> call, @NonNull Response<ResponseForTop> response) {

                    ResponseForTop responseForTop = response.body();

                    assert responseForTop != null;

                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    animeAdapter.setData(responseForTop.getAnime());

                }

                @Override
                public void onFailure(@NonNull Call<ResponseForTop> call, @NonNull Throwable t) {

                }
            });

        } else
            Toast.makeText(getActivity(), R.string.check_connection, Toast.LENGTH_SHORT).show();
    }

    private void getAnimeByGenre(String genre) {

        if(checkConnection(Objects.requireNonNull(getActivity()))) {

        Call<ResponseByGenre> call = client.animeFromGenre(genre);

        call.enqueue(new Callback<ResponseByGenre>() {
            @Override
            public void onResponse(@NonNull Call<ResponseByGenre> call, @NonNull Response<ResponseByGenre> response) {
                ResponseByGenre responseByGenre = response.body();
                assert responseByGenre != null;
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                animeAdapter.setData(responseByGenre.getAnime());
            }

            @Override
            public void onFailure(@NonNull Call<ResponseByGenre> call, @NonNull Throwable t) {


            }
        });
    }else
            Toast.makeText(getActivity(), R.string.check_connection, Toast.LENGTH_SHORT).show();
    }


    private void searchForAnimeByName(String animeName){


        if(checkConnection(Objects.requireNonNull(getActivity()))) {



        Retrofit retrofit = builder.build();
        AnimeClient client = retrofit.create(AnimeClient.class);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);

        Call<ResponseForAnimeSearch> animeSearch = client.searchAnimeByName(animeName);
        animeSearch.enqueue(new Callback<ResponseForAnimeSearch>() {
            @Override
            public void onResponse(@NonNull Call<ResponseForAnimeSearch> call, @NonNull Response<ResponseForAnimeSearch> response) {

                ArrayList<Anime> animes = new ArrayList<>();
                Anime tmpAnime;
                Result tmpResult;
                if(response.body() != null){
                List<Result> results = response.body().getResults();

                for (int i = 0; i < results.size(); i++) {

                    tmpResult = results.get(i);

                    tmpAnime = new Anime(tmpResult.getMalId(), tmpResult.getTitle(), tmpResult.getImageUrl(), tmpResult.getStartDate(), tmpResult.getScore());

                    animes.add(tmpAnime);
                }


                }
                animeAdapter.setData(animes);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(@NonNull Call<ResponseForAnimeSearch> call, @NonNull Throwable t) {

            }
        });
        }else
            Toast.makeText(getActivity(), R.string.check_connection, Toast.LENGTH_SHORT).show();
    }

    private void getFavoriteAnimes(){

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        userID = user.getUid();

        readData(new MyCallback() {
            @Override
            public void onCallback(List<AnimeDetailed> list) {
                ArrayList<Anime> animes = new ArrayList<>();
                Anime tmpAnime;
                AnimeDetailed tmpAnimeDetailed;

                for (int i = 0; i < list.size(); i++) {

                    tmpAnimeDetailed = list.get(i);

                    tmpAnime = new Anime(tmpAnimeDetailed.getMalId(),tmpAnimeDetailed.getTitle(),tmpAnimeDetailed.getImageUrl()," ",tmpAnimeDetailed.getScore());

                    animes.add(tmpAnime);


                }
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                animeAdapter.setData(animes);

            }
        });
    }


    public interface MyCallback {
        void onCallback(List<AnimeDetailed> list);
    }

    public void readData(final MyCallback myCallback) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                favorites.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    for (DataSnapshot favSnapshot : ds.child(userID).getChildren()) {
                        favorites.add(favSnapshot.getValue(AnimeDetailed.class));
                    }
                }
                myCallback.onCallback(favorites);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static boolean checkConnection(Context context) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
