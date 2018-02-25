package com.projects.cactus.el_kollia.model;

/**
 * Created by el on 4/15/2017.
 */

public class QuestionRequest {

    private String userId;

    public QuestionRequest() {
    }


    public QuestionRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
