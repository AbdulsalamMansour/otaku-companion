package com.example.abdulsalam.otakucompanion.Model.Responses;


import com.example.abdulsalam.otakucompanion.Model.SharedClasses.Anime;
import com.example.abdulsalam.otakucompanion.Model.SharedClasses.MalUrl;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseByGenre {

    @SerializedName("request_hash")
    @Expose
    private String requestHash;
    @SerializedName("request_cached")
    @Expose
    private Boolean requestCached;
    @SerializedName("request_cache_expiry")
    @Expose
    private Integer requestCacheExpiry;
    @SerializedName("mal_url")
    @Expose
    private MalUrl malUrl;
    @SerializedName("item_count")
    @Expose
    private Integer itemCount;
    @SerializedName("anime")
    @Expose
    private List<Anime> anime = null;

    public String getRequestHash() {
        return requestHash;
    }

    public void setRequestHash(String requestHash) {
        this.requestHash = requestHash;
    }

    public Boolean getRequestCached() {
        return requestCached;
    }

    public void setRequestCached(Boolean requestCached) {
        this.requestCached = requestCached;
    }

    public Integer getRequestCacheExpiry() {
        return requestCacheExpiry;
    }

    public void setRequestCacheExpiry(Integer requestCacheExpiry) {
        this.requestCacheExpiry = requestCacheExpiry;
    }

    public MalUrl getMalUrl() {
        return malUrl;
    }

    public void setMalUrl(MalUrl malUrl) {
        this.malUrl = malUrl;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public List<Anime> getAnime() {
        return anime;
    }

    public void setAnime(List<Anime> anime) {
        this.anime = anime;
    }

}


