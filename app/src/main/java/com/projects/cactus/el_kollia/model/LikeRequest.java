package com.projects.cactus.el_kollia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by el on 5/12/2017.
 */

public class LikeRequest {

    @SerializedName("question_id")
    private String questionId;

    @SerializedName("user_id")
    private String String_user;


    public LikeRequest(String questionId, String string_user) {
        this.questionId = questionId;
        String_user = string_user;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getString_user() {
        return String_user;
    }

    public void setString_user(String string_user) {
        String_user = string_user;
    }

//    public static final String DECREASE_UP_VOTES = "decrease_up_votes";
//    public static final String INCREASE_UP_VOTES = "increase_up_votes";
//    public static final String INCREASE_DOWN_VOTES = "increase_down_votes";
//    public static final String DECREASE_DOWN_VOTES = "decrease_down_votes";


}
