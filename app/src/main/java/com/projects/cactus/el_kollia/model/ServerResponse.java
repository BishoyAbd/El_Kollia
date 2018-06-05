package com.projects.cactus.el_kollia.model;

/**
 * Created by Bishoy on 2/28/2017.
 */

//server response to user logging in and signing up

public class ServerResponse {

    private String message;
    private boolean error;

    public String getMessage() {
        return message;
    }


    public boolean getError() {
        return error;
    }
}