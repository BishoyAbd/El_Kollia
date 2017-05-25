package com.projects.cactus.el_kollia.model;

/**
 * Created by el on 5/14/2017.
 */
public class Answer {

    private String answer;
    private String id;
    private String user_id;
    private String question_id;
    private String date;
    private String profile_url;
    private String user;

    public Answer() {
    }

    public Answer(String answer, String id, String user_id, String question_id, String date, String profile_url) {
        this.answer = answer;
        this.id = id;
        this.user_id = user_id;
        this.question_id = question_id;
        this.date = date;
        this.profile_url=profile_url;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
