package com.projects.cactus.el_kollia.model;

/**
 * Created by Bishoy on 2/4/2017.
 */


//for uploading product
public class Respond {

    private String message;
    private Boolean error;

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

}