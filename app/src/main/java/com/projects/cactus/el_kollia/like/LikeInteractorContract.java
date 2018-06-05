package com.projects.cactus.el_kollia.like;

import com.projects.cactus.el_kollia.model.LikeRequest;
import com.projects.cactus.el_kollia.model.LikeResponse;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.ServerResponse;

import io.reactivex.Observable;

/**
 * Created by Bishoy Abd on 3/8/2018.
 */

public interface LikeInteractorContract {


    // Observable<LikeResponse> isLicked(String userId, int questionId);

    Observable<LikeResponse> like(LikeRequest likeRequest);

    Observable<LikeResponse> doLike(Question question);


}
