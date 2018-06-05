package com.projects.cactus.el_kollia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bishoy Abd on 3/23/2018.
 */

public class LikeResponse {

    @SerializedName("likes")
    private int likes;

    @SerializedName("success")
    private boolean isSuccessful;


    public LikeResponse() {
    }


    public boolean isSuccessful() {
        return isSuccessful;
    }

    public int getLikes() {
        return likes;
    }


    public void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}

