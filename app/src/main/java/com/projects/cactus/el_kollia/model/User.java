package com.projects.cactus.el_kollia.model;

/**
 * Created by Bishoy on 2/28/2017.
 */

public class User {

    private String name;
    private String email; //pluplu@el-eng.menoufia.edu.eg
    private String unique_id;
    private String phone_number;  //optional
    private String password;
  //  private String old_password;
   // private String new_password;
    private String academic_year; //1 2 3 4 5
    private String department;  //control,cs,communication
    private int classification; //1 stuff--0 student
    public User(){


    }

    public User(String name, String email, String unique_id, String phone_number, String password, String academic_year, String department, int classification) {
        this.name = name;
        this.email = email;
        this.unique_id = unique_id;
        this.phone_number = phone_number;
        this.password = password;
        this.academic_year = academic_year;
        this.department = department;
        this.classification = classification;
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


    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getPhoneNumber() {
        return getPhone_number();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.setPhone_number(phoneNumber);
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public String getAcademic_year() {
        return academic_year;
    }

    public void setAcademic_year(String academic_year) {
        this.academic_year = academic_year;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }
}