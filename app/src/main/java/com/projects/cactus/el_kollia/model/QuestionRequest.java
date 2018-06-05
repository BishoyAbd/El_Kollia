package com.projects.cactus.el_kollia.model;

/**
 * Created by el on 4/15/2017.
 */

/**
 * question request to post a question to server
 */

public class QuestionRequest {

    private String askedBy;
    private String question;
    private String subjectId;

    public QuestionRequest() {
    }

    public QuestionRequest(String askedBy, String question, String subjectId) {
        this.askedBy = askedBy;
        this.question = question;
        this.subjectId = subjectId;
    }


}
