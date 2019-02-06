package com.example.abdulsalam.otakucompanion.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdulsalam.otakucompanion.Model.SharedClasses.Anime;
import com.example.abdulsalam.otakucompanion.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    private List<Anime> animes;
    private Context context;
    private OnClickHandler onClickHandler;

    public interface OnClickHandler{
        void onItemClick(Anime anime);
    }


    public AnimeAdapter(Context context, OnClickHandler onClickHandler) {
        this.context = context;
        this.onClickHandler = onClickHandler;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutId = R.layout.anime_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, viewGroup, false);
        AnimeViewHolder animeViewHolder = new AnimeViewHolder(view);
        return animeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder animeViewHolder, int position) {

        animeViewHolder.bind(position);

    }

    @Override
    public int getItemCount() {

        if(animes == null)
            return 0;

        return animes.size();
    }

    public void setData(List<Anime> animes){
        this.animes = animes;
        notifyDataSetChanged();
    }



    class AnimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView posterView;
        TextView animeTitle;

        AnimeViewHolder(@NonNull View itemView) {
            super(itemView);

            posterView = itemView.findViewById(R.id.img_poster);
            animeTitle = itemView.findViewById(R.id.anime_name);
            itemView.setOnClickListener(this);

        }


        void bind(int position) {
            Anime anime = animes.get(position);
            if(anime.getImageUrl() != null)
                Picasso.with(context).load(anime.getImageUrl()).into(posterView);
            animeTitle.setText(anime.getTitle());

        }

        @Override
        public void onClick(View view) {
            Anime anime = animes.get(getAdapterPosition());
            onClickHandler.onItemClick(anime);
        }
    }
}
