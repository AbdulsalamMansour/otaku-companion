package com.example.abdulsalam.otakucompanion;

import com.example.abdulsalam.otakucompanion.Model.Responses.ResponseByGenre;
import com.example.abdulsalam.otakucompanion.Model.Responses.ResponseForAnimeSearch;
import com.example.abdulsalam.otakucompanion.Model.Responses.ResponseForCharacters;
import com.example.abdulsalam.otakucompanion.Model.Responses.ResponseForTop;
import com.example.abdulsalam.otakucompanion.Model.SharedClasses.AnimeDetailed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AnimeClient {


    @GET("/v3/top/anime/")
    Call<ResponseForTop> topAnime();

    @GET("/v3/genre/anime/{genre}/")
    Call<ResponseByGenre> animeFromGenre(@Path("genre") String genreId);

    @GET("/v3/anime/{anime_id}/characters_staff/")
    Call<ResponseForCharacters> charactersFromAnime(@Path("anime_id") String anime_id);

    @GET("/v3/search/anime/")
    Call<ResponseForAnimeSearch> searchAnimeByName(@Query("q") String anime_name);

    @GET("/v3/anime/{anime_id}/")
    Call<AnimeDetailed> getAnimeByID(@Path("anime_id") String anime_id);


}


