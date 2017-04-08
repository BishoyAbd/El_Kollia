package com.projects.cactus.el_kollia.model;

/**
 * Created by Bishoy on 2/28/2017.
 */

//server request for user login and sign up operations
public class ServerRequest {

    private String operation;
    private User user;


    public ServerRequest(){

    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }
}