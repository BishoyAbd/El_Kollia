package com.projects.cactus.el_kollia.feed;

import com.projects.cactus.el_kollia.ApiServices.QuestionApiService;
import com.projects.cactus.el_kollia.data.LocalAuthStore;
import com.projects.cactus.el_kollia.like.LikeInteractorContract;
import com.projects.cactus.el_kollia.model.LikeResponse;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.QuestionRequest;
import com.projects.cactus.el_kollia.model.ServerResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by Bishoy Abd on 6/8/2017.
 * ..
 */

public class FeedInteractorImp implements FeedInteractor {


    private QuestionApiService apiService;
    private LocalAuthStore localAuthStore;
    private LikeInteractorContract likeInteractor;


    @Inject
    public FeedInteractorImp(QuestionApiService apiService, LocalAuthStore localAuthStore) {
        this.apiService = apiService;
        this.localAuthStore = localAuthStore;
    }

    @Override
    public Observable<List<Question>> getPosts(QuestionRequest questionRequest) {

//        return localAuthStore.getUserId(AppConstants.KEY_USER_ID)
//                .flatMap(userId -> {
//                    questionRequest.setUserId(userId);
//                    return apiService.loadQuestions(questionRequest);
//                });
        return apiService.loadQuestions(questionRequest);
    }

    @Override
    public Observable<ServerResponse> post(QuestionRequest questionRequest) {
        return apiService.postQuestion(questionRequest);
    }

    @Override
    public Observable<ServerResponse> follow(String questionId) {
        return null;
    }

    @Override
    public Observable<ServerResponse> bookmark(String answerId) {
        return null;
    }

    @Override
    public Observable<ServerResponse> pass(String userToPassId, String questionId) {
        return null;
    }

    @Override
    public Observable<ServerResponse> addAnswer(String answer, String questionId) {
        return null;
    }

    @Override
    public Observable<ServerResponse> removeAnswer(String answerId, String questionId) {
        return null;
    }

    @Override
    public Observable<LikeResponse> upVoteAnswer(String answerId, String questionId) {
        return null;
    }

    @Override
    public Observable<LikeResponse> downVoteAnswer(String answerId, String questionId) {
        return null;
    }
}


//to solve the upvote problem
//solution 1
//get all the userIds who upvoted along with every post
//and search for this if the user id contained in the list make it green
//sole 2 is the one ur using currently
