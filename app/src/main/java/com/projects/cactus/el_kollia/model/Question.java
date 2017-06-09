package com.projects.cactus.el_kollia.model;

/**
 * Created by el on 4/18/2017.
 */

public class Question {

    //  private int question_id;
    private String question;
    private String unique_id;
    private String course;
    private String date;
    private String question_id;
    private String user_profile;
    private int academic_year;
    private String department;
    private  String user_name;
    private int up_votes;
    private int down_votes;
    private int num_of_comments;




    public Question(String question, String unique_id, String course, String date,
                    String question_id, String user_profile_photo, int academic_year,
                    String user_name, String department) {
        this.question = question;
        this.unique_id = unique_id;
        this.course = course;
        this.date = date;
        this.question_id = question_id;
        this.user_profile = user_profile_photo;
        this.academic_year = academic_year;
        this.user_name=user_name;
        this.department=department;
    }

    public  Question(){

    }

//    public int getQuestion_id() {
//        return question_id;
//    }
//
//    public void setQuestion_id(int question_id) {
//        this.question_id = question_id;
//    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getAcademic_year() {
        return academic_year;
    }

    public void setAcademic_year(int academic_year) {
        this.academic_year = academic_year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getUser_profile_photo() {
        return user_profile;
    }

    public void setUser_profile_photo(String user_profile_photo) {
        this.user_profile = user_profile_photo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUp_votes() {
        return up_votes;
    }

    public void setUp_votes(int up_votes) {
        this.up_votes = up_votes;
    }

    public int getDown_votes() {
        return down_votes;
    }

    public void setDown_votes(int down_votes) {
        this.down_votes = down_votes;
    }

    public int getNum_of_comments() {
        return num_of_comments;
    }

    public void setNum_of_comments(int num_of_comments) {
        this.num_of_comments = num_of_comments;
    }
}
