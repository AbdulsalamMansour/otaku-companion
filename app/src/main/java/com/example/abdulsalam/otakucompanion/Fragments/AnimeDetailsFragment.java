package com.example.abdulsalam.otakucompanion.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdulsalam.otakucompanion.AnimeClient;
import com.example.abdulsalam.otakucompanion.Model.SharedClasses.Anime;
import com.example.abdulsalam.otakucompanion.Model.SharedClasses.AnimeDetailed;
import com.example.abdulsalam.otakucompanion.OtakuWidget.OtakuWidgetService;
import com.example.abdulsalam.otakucompanion.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeDetailsFragment extends Fragment {

    @BindView(R.id.img_poster)
    ImageView imgPoster;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_synopsis)
    TextView txtSynopsis;
    @BindView(R.id.txt_score)
    TextView txtScore;
    @BindView(R.id.txt_airing_start)
    TextView txtAiringStart;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.layout_details)
    LinearLayout linearLayout;

    Anime anime;
    AnimeDetailed animeDetailed;
    private DatabaseReference myRef;
    ImageView mFavImg;
    private int flag = 0;

    private String userID;
    private Unbinder mUnbinder = null ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_anime_details, container, false);
        mUnbinder = ButterKnife.bind(this,rootView);

        AdView mAdView =  rootView.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);



        progressBar.setVisibility(View.VISIBLE);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);

        if(savedInstanceState != null) {
            animeDetailed = savedInstanceState.getParcelable(getString(R.string.anime_detailed));
            Picasso.with(getActivity()).load(animeDetailed.getImageUrl()).into(imgPoster);
            //set the title
            txtTitle.setText(animeDetailed.getTitle());
            //set the synopsis
            txtSynopsis.setText(animeDetailed.getSynopsis());
            //set the score
            txtScore.setText(animeDetailed.getScore().toString());
            //set airing date
            txtAiringStart.setText(animeDetailed.getAired().getFrom());
        } else {
            assert getArguments() != null;
            anime = getArguments().getParcelable(getString(R.string.anime_key));
            assert anime != null;
            getAnimeByID(anime.getMalId().toString());
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        userID = user.getUid();


        mFavImg = rootView.findViewById(R.id.fav_btn);
        readData(new AnimeDetailsFragment.MyCallback() {
            @Override
            public void onCallback(final int flag2) {
                mFavImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(flag2 == 0){
                            mFavImg.setImageResource(R.drawable.added);
                            myRef.child(getString(R.string.fav)).child(userID).child(anime.getMalId().toString()).setValue(anime);
                            flag = 1;
                        }
                        else
                        if(flag2 == 1){
                            myRef.child(getString(R.string.fav)).child(userID).child(anime.getMalId().toString()).removeValue();
                            mFavImg.setImageResource(R.drawable.removed);
                            flag = 0;
                        }

                    }
                });
            }
        });

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    // --------------------- Methods to handle the case of an Anime from TOP view ---------------------


    private void getAnimeByID(final String animeID){

        if(checkConnection(getActivity())) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://api.jikan.moe")
                    .addConverterFactory(GsonConverterFactory.create());


            Retrofit retrofit = builder.build();
            AnimeClient client = retrofit.create(AnimeClient.class);


            Call<AnimeDetailed> getAnime = client.getAnimeByID(animeID);


            getAnime.enqueue(new Callback<AnimeDetailed>() {
                @Override
                public void onResponse(Call<AnimeDetailed> call, Response<AnimeDetailed> response) {

                    animeDetailed = response.body();
                    //set the poster
                    Picasso.with(getActivity()).load(animeDetailed.getImageUrl()).into(imgPoster);
                    //set the title
                    txtTitle.setText(animeDetailed.getTitle());
                    //set the synopsis
                    txtSynopsis.setText(animeDetailed.getSynopsis());
                    //set the score
                    txtScore.setText(animeDetailed.getScore().toString());
                    //set airing date
                    txtAiringStart.setText(animeDetailed.getAired().getFrom());

                    progressBar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    OtakuWidgetService.startActionUpdateWidgets(getActivity(), animeDetailed.getSynopsis(), animeDetailed);


                }

                @Override
                public void onFailure(Call<AnimeDetailed> call, Throwable t) {

                    Toast.makeText(getContext(), "no i didn't", Toast.LENGTH_SHORT).show();

                }
            });

        }else
            Toast.makeText(getActivity(), R.string.check_connection, Toast.LENGTH_SHORT).show();

    }

    public interface MyCallback {
        void onCallback(int flag2);
    }

    public void readData(final AnimeDetailsFragment.MyCallback myCallback) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    for (DataSnapshot favSnapshot : ds.child(userID).getChildren()) {
                        if(anime != null)
                        if(favSnapshot.getKey() != null && favSnapshot.getKey().equals(anime.getMalId().toString())) {
                            flag = 1;
                            mFavImg.setImageResource(R.drawable.added);
                        }
                    }


                }
                myCallback.onCallback(flag);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getString(R.string.anime_detailed), animeDetailed );
    }

    public static boolean checkConnection(Context context) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
