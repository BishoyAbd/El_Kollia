package com.projects.cactus.el_kollia.like;

import com.projects.cactus.el_kollia.ApiServices.QuestionApiService;
import com.projects.cactus.el_kollia.model.LikeRequest;
import com.projects.cactus.el_kollia.model.LikeResponse;
import com.projects.cactus.el_kollia.model.Question;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by Bishoy Abd on 3/8/2018.
 */

public class LikeInteractor implements LikeInteractorContract {


    private QuestionApiService questionApiService;

    @Inject
    public LikeInteractor(QuestionApiService questionApiService) {
        this.questionApiService = questionApiService;
    }


    @Override
    public Observable<LikeResponse> like(LikeRequest likeRequest) {
        return null;
    }

    @Override
    public Observable<LikeResponse> doLike(Question question) {

        //test
        LikeResponse likeResponse = new LikeResponse();
        likeResponse.setIsSuccessful(true);
        likeResponse.setLikes(10);

        return Observable.just(likeResponse);
    }

    //simulating network request

    private void testSleep() {

        try {
            Thread.sleep(3000);
            Timber.d(Thread.currentThread().getName() + "slept");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
