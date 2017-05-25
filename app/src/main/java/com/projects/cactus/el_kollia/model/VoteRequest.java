package com.projects.cactus.el_kollia.model;

/**
 * Created by el on 5/12/2017.
 */

public class VoteRequest {

    private Question question;
    private String command;
    private String code;  //up_vote_request or down_vote_request

    public static final String DECREASE_UP_VOTES="decrease_up_votes";
    public static final String INCREASE_UP_VOTES="increase_up_votes";
    public static final String INCREASE_DOWN_VOTES="increase_down_votes";
    public static final String DECREASE_DOWN_VOTES="decrease_down_votes";



    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
