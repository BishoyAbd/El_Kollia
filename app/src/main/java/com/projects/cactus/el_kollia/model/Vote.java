package com.projects.cactus.el_kollia.model;

/**
 * Created by el on 6/14/2017.
 */

public class Vote {


    private boolean upvoted;
    private String message;


    public Vote(boolean upvoted, String message) {
        this.upvoted = upvoted;
        this.message = message;
    }

    public boolean isUpvoted() {
        return upvoted;
    }

    public void setUpvoted(boolean upvoted) {
        this.upvoted = upvoted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
