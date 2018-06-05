package com.projects.cactus.el_kollia.feed;

import com.projects.cactus.el_kollia.model.LikeResponse;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.QuestionRequest;
import com.projects.cactus.el_kollia.model.ServerResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by el on 6/8/2017.
 */

public interface FeedInteractor {


    Observable<List<Question>> getPosts(QuestionRequest questionRequest);

    Observable<ServerResponse> post(QuestionRequest questionRequest);


    /*
 follow a specific answer
  */
    Observable<ServerResponse> follow(String questionId);

    /*
    save this answer to read later
     */
    Observable<ServerResponse> bookmark(String answerId);

    /*
    ask another user to answer this question
     */
    Observable<ServerResponse> pass(String userToPassId, String questionId);

    //no user Id because the interactor will get it from the AuthenticationStore
    Observable<ServerResponse> addAnswer(String answer, String questionId);

    /*
    I added both question and answer ids , to somehow make the server query for the question's answers first
     then search for the answer form the answers on this question
     */
    Observable<ServerResponse> removeAnswer(String answerId, String questionId);

    Observable<LikeResponse> upVoteAnswer(String answerId, String questionId);

    Observable<LikeResponse> downVoteAnswer(String answerId, String questionId);


}
