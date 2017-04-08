package com.projects.cactus.el_kollia.model;

/**
 * Created by Bishoy on 2/28/2017.
 */

public class User {

    private String name;
    private String email;
    private String unique_id;
    private String phone_number;
    private String password;
    private String old_password;
    private String new_password;
    public User(){


    }

    public User(String name, String unique_id, String phoneNumber, String password) {
        this.name = name;
        this.unique_id = unique_id;
        this.phone_number = phoneNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }
}