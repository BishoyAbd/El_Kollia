package com.projects.cactus.el_kollia.model;

/**
 * Created by Bishoy on 2/28/2017.
 *
 *
 */

//server response to user logging in and signing up

public class ServerResponse {

    private String result;
    private String message;
    private User user;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}