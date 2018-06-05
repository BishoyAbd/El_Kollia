package com.projects.cactus.el_kollia.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by el on 4/18/2017.
 */

public class Question {

    //  private int question_id;
    private String id;
    private String question;
    @SerializedName("askedBy")
    private String askedBy; //question poster id
    private String course;
    private String date;
    private String user_profile;
    private int academic_year;
    private String department;
    private String user_name;
    private int up_votes;
    private int down_votes;
    private int num_of_comments;
    private String likes;
    private List<TAG> tags;
    @SerializedName("is_liked") //is liked by this user or not , used to change the like btn state
    private boolean isLiked;


    public Question(String question, String askedBy, String course, String date,
                    String user_profile_photo, int academic_year,
                    String user_name, String department, String likes, List<TAG> tags) {
        this.question = question;
        this.askedBy = askedBy;
        this.course = course;
        this.date = date;
        this.user_profile = user_profile_photo;
        this.academic_year = academic_year;
        this.user_name = user_name;
        this.department = department;
        this.likes = likes;
        this.tags = tags;
    }

    public Question() {

    }


    public Question(String userId, String question, String date, boolean isLiked) {
        this.askedBy = userId;
        this.question = question;
        this.date = date;
        this.isLiked = isLiked;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAskedBy() {
        return askedBy;
    }

    public void setAskedBy(String askedBy) {
        this.askedBy = askedBy;
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

    public String getLikesIds() {
        return likes;
    }

    public void setLikesIds(String likesIds) {
        this.likes = likesIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TAG> getTags() {
        return tags;
    }

    public void setTags(List<TAG> tags) {
        this.tags = tags;
    }

    public boolean IsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", askedBy='" + askedBy + '\'' +
                ", date='" + date + '\'' +
                ", likes='" + likes + '\'' +
                ", isLiked=" + isLiked +
                '}';
    }


    static class Builder {


        //_id
        private String id;
        private String question;
        private String askedBy; //question poster id
        private String subjectId;
        private String date;
        private String profilePhoto;
        private int academicYear;
        private String department;
        private String userName;
        private String visibleName;
        private int up_votes;
        private int down_votes;
        private int num_of_comments;
        private String likes;
        private List<TAG> tags;
        @SerializedName("is_liked")
        //is liked by this user or not , used to change the like btn state
        private boolean isLiked;


        public Builder() {
        }


        Builder question(String question) {
            this.question = question;
            return this;
        }


        Builder askedBy(String user_id) {
            this.askedBy = user_id;
            return this;
        }

        Builder date(String date) {
            this.date = date;
            return this;
        }

        Builder profilePhoto(String profilePhoto) {
            this.profilePhoto = profilePhoto;
            return this;
        }

        Builder academicYear(int academicYear) {
            this.academicYear = academicYear;
            return this;
        }

        Builder department(String department) {
            this.department = department;
            return this;
        }

        Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        Builder visibleName(String visibleName) {
            this.visibleName = visibleName;
            return this;
        }


    }
}
