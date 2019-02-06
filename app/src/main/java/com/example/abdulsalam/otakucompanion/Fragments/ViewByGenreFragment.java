package com.example.abdulsalam.otakucompanion.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.abdulsalam.otakucompanion.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ViewByGenreFragment extends Fragment {




    @BindView(R.id.lv_genres)
    ListView lvGenres;
    public ViewByGenreFragment() {}

    private Unbinder mUnbinder = null ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_by_genre, container, false);
        mUnbinder = ButterKnife.bind(this,rootView);
        String [] genres = getResources().getStringArray(R.array.genres);

        ListAdapter listAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()),android.R.layout.simple_list_item_1,genres);

        lvGenres.setAdapter(listAdapter);

        lvGenres.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(getString(R.string.position),i);

                        MainDisplayFragment mainDisplayFragment = new MainDisplayFragment();

                        mainDisplayFragment.setArguments(bundle);

                        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                mainDisplayFragment).addToBackStack(null).commit();



                    }
                }
        );


        return rootView;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
