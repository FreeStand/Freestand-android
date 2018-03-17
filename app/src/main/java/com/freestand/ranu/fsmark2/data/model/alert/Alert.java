package com.freestand.ranu.fsmark2.data.model.alert;

/**
 * Created by prateek on 04/02/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alert {

    @SerializedName("badge")
    private String badge;
    @SerializedName("body")
    private String body;
    @SerializedName("date")
    private String date;
    @SerializedName("title")
    private String title;

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}