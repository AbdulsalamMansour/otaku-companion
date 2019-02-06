package com.example.abdulsalam.otakucompanion.Model.SharedClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Aired {

    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private Object to;
    @SerializedName("string")
    @Expose
    private String string;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Object getTo() {
        return to;
    }

    public void setTo(Object to) {
        this.to = to;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}