package com.projects.cactus.el_kollia.authentication.model;

import com.google.gson.annotations.SerializedName;
import com.projects.cactus.el_kollia.model.User;

/**
 * Created by Bishoy on 2/28/2017.
 */

//server request for user login and sign up operations
public class ServerRequest {

    @SerializedName("operation")
    private String operation;
    @SerializedName("user")
    private User user;


     ServerRequest() {

    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }
}