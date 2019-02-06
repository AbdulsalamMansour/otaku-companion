package com.example.abdulsalam.otakucompanion.Model.SharedClasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Anime implements Parcelable {

    @SerializedName("mal_id")
    @Expose
    private Integer malId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("airing_start")
    @Expose
    private String airingStart;
    @SerializedName("episodes")
    @Expose
    private Integer episodes;
    @SerializedName("members")
    @Expose
    private Integer members;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("producers")
    @Expose
    private List<Producer> producers = null;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("licensors")
    @Expose
    private List<String> licensors = null;
    @SerializedName("r18")
    @Expose
    private Boolean r18;
    @SerializedName("kids")
    @Expose
    private Boolean kids;

    protected Anime(Parcel in) {
        if (in.readByte() == 0) {
            malId = null;
        } else {
            malId = in.readInt();
        }
        url = in.readString();
        title = in.readString();
        imageUrl = in.readString();
        synopsis = in.readString();
        type = in.readString();
        airingStart = in.readString();
        if (in.readByte() == 0) {
            episodes = null;
        } else {
            episodes = in.readInt();
        }
        if (in.readByte() == 0) {
            members = null;
        } else {
            members = in.readInt();
        }
        source = in.readString();
        if (in.readByte() == 0) {
            score = null;
        } else {
            score = in.readDouble();
        }
        licensors = in.createStringArrayList();
        byte tmpR18 = in.readByte();
        r18 = tmpR18 == 0 ? null : tmpR18 == 1;
        byte tmpKids = in.readByte();
        kids = tmpKids == 0 ? null : tmpKids == 1;
    }

    public static final Creator<Anime> CREATOR = new Creator<Anime>() {
        @Override
        public Anime createFromParcel(Parcel in) {
            return new Anime(in);
        }

        @Override
        public Anime[] newArray(int size) {
            return new Anime[size];
        }
    };

    public Anime(Integer malId, String title, String imageUrl, String airingStart, Double score) {
        this.malId = malId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.airingStart = airingStart;
        this.score = score;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAiringStart() {
        return airingStart;
    }

    public void setAiringStart(String airingStart) {
        this.airingStart = airingStart;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<String> getLicensors() {
        return licensors;
    }

    public void setLicensors(List<String> licensors) {
        this.licensors = licensors;
    }

    public Boolean getR18() {
        return r18;
    }

    public void setR18(Boolean r18) {
        this.r18 = r18;
    }

    public Boolean getKids() {
        return kids;
    }

    public void setKids(Boolean kids) {
        this.kids = kids;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (malId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(malId);
        }
        parcel.writeString(url);
        parcel.writeString(title);
        parcel.writeString(imageUrl);
        parcel.writeString(synopsis);
        parcel.writeString(type);
        parcel.writeString(airingStart);
        if (episodes == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(episodes);
        }
        if (members == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(members);
        }
        parcel.writeString(source);
        if (score == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(score);
        }
        parcel.writeStringList(licensors);
        parcel.writeByte((byte) (r18 == null ? 0 : r18 ? 1 : 2));
        parcel.writeByte((byte) (kids == null ? 0 : kids ? 1 : 2));
    }
}
