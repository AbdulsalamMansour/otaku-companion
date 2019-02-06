package com.example.abdulsalam.otakucompanion.Model.SharedClasses;



import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnimeDetailed implements Parcelable {

    @SerializedName("request_hash")
    @Expose
    private String requestHash;
    @SerializedName("request_cached")
    @Expose
    private Boolean requestCached;
    @SerializedName("request_cache_expiry")
    @Expose
    private Integer requestCacheExpiry;
    @SerializedName("mal_id")
    @Expose
    private Integer malId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("trailer_url")
    @Expose
    private String trailerUrl;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title_english")
    @Expose
    private String titleEnglish;
    @SerializedName("title_japanese")
    @Expose
    private String titleJapanese;
    @SerializedName("title_synonyms")
    @Expose
    private List<String> titleSynonyms = null;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("episodes")
    @Expose
    private Object episodes;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("airing")
    @Expose
    private Boolean airing;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("scored_by")
    @Expose
    private Integer scoredBy;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("popularity")
    @Expose
    private Integer popularity;
    @SerializedName("members")
    @Expose
    private Integer members;
    @SerializedName("favorites")
    @Expose
    private Integer favorites;
    @SerializedName("aired")
    @Expose
    private Aired aired;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("premiered")
    @Expose
    private String premiered;
    @SerializedName("broadcast")
    @Expose
    private String broadcast;
    @SerializedName("producers")
    @Expose
    private List<Producer> producers = null;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    @SerializedName("opening_themes")
    @Expose
    private List<String> openingThemes = null;
    @SerializedName("ending_themes")
    @Expose
    private List<String> endingThemes = null;

    public AnimeDetailed() {
    }

    protected AnimeDetailed(Parcel in) {
        requestHash = in.readString();
        byte tmpRequestCached = in.readByte();
        requestCached = tmpRequestCached == 0 ? null : tmpRequestCached == 1;
        if (in.readByte() == 0) {
            requestCacheExpiry = null;
        } else {
            requestCacheExpiry = in.readInt();
        }
        if (in.readByte() == 0) {
            malId = null;
        } else {
            malId = in.readInt();
        }
        url = in.readString();
        imageUrl = in.readString();
        trailerUrl = in.readString();
        title = in.readString();
        titleEnglish = in.readString();
        titleJapanese = in.readString();
        titleSynonyms = in.createStringArrayList();
        type = in.readString();
        source = in.readString();
        status = in.readString();
        byte tmpAiring = in.readByte();
        airing = tmpAiring == 0 ? null : tmpAiring == 1;
        duration = in.readString();
        rating = in.readString();
        if (in.readByte() == 0) {
            score = null;
        } else {
            score = in.readDouble();
        }
        if (in.readByte() == 0) {
            scoredBy = null;
        } else {
            scoredBy = in.readInt();
        }
        if (in.readByte() == 0) {
            rank = null;
        } else {
            rank = in.readInt();
        }
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readInt();
        }
        if (in.readByte() == 0) {
            members = null;
        } else {
            members = in.readInt();
        }
        if (in.readByte() == 0) {
            favorites = null;
        } else {
            favorites = in.readInt();
        }
        synopsis = in.readString();
        background = in.readString();
        premiered = in.readString();
        broadcast = in.readString();
        openingThemes = in.createStringArrayList();
        endingThemes = in.createStringArrayList();
    }

    public static final Creator<AnimeDetailed> CREATOR = new Creator<AnimeDetailed>() {
        @Override
        public AnimeDetailed createFromParcel(Parcel in) {
            return new AnimeDetailed(in);
        }

        @Override
        public AnimeDetailed[] newArray(int size) {
            return new AnimeDetailed[size];
        }
    };

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

    public Aired getAired() {
        return aired;
    }

    public void setAired(Aired aired) {
        this.aired = aired;
    }

    public Integer getMalId() {
        return malId;
    }

    public void setMalId(Integer malId) {
        this.malId = malId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public String getTitleJapanese() {
        return titleJapanese;
    }

    public void setTitleJapanese(String titleJapanese) {
        this.titleJapanese = titleJapanese;
    }

    public List<String> getTitleSynonyms() {
        return titleSynonyms;
    }

    public void setTitleSynonyms(List<String> titleSynonyms) {
        this.titleSynonyms = titleSynonyms;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Object getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Object episodes) {
        this.episodes = episodes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getAiring() {
        return airing;
    }

    public void setAiring(Boolean airing) {
        this.airing = airing;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getScoredBy() {
        return scoredBy;
    }

    public void setScoredBy(Integer scoredBy) {
        this.scoredBy = scoredBy;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public Integer getFavorites() {
        return favorites;
    }

    public void setFavorites(Integer favorites) {
        this.favorites = favorites;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getPremiered() {
        return premiered;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<String> getOpeningThemes() {
        return openingThemes;
    }

    public void setOpeningThemes(List<String> openingThemes) {
        this.openingThemes = openingThemes;
    }

    public List<String> getEndingThemes() {
        return endingThemes;
    }

    public void setEndingThemes(List<String> endingThemes) {
        this.endingThemes = endingThemes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(requestHash);
        parcel.writeByte((byte) (requestCached == null ? 0 : requestCached ? 1 : 2));
        if (requestCacheExpiry == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(requestCacheExpiry);
        }
        if (malId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(malId);
        }
        parcel.writeString(url);
        parcel.writeString(imageUrl);
        parcel.writeString(trailerUrl);
        parcel.writeString(title);
        parcel.writeString(titleEnglish);
        parcel.writeString(titleJapanese);
        parcel.writeStringList(titleSynonyms);
        parcel.writeString(type);
        parcel.writeString(source);
        parcel.writeString(status);
        parcel.writeByte((byte) (airing == null ? 0 : airing ? 1 : 2));
        parcel.writeString(duration);
        parcel.writeString(rating);
        if (score == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(score);
        }
        if (scoredBy == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(scoredBy);
        }
        if (rank == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(rank);
        }
        if (popularity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(popularity);
        }
        if (members == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(members);
        }
        if (favorites == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(favorites);
        }
        parcel.writeString(synopsis);
        parcel.writeString(background);
        parcel.writeString(premiered);
        parcel.writeString(broadcast);
        parcel.writeStringList(openingThemes);
        parcel.writeStringList(endingThemes);
    }
}