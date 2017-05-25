package com.projects.cactus.el_kollia.ApiServices;

import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.Respond;
import com.projects.cactus.el_kollia.model.VoteRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by el on 4/15/2017.
 */
public interface QuestionPost {



@POST("elkollya/post_question.php")
Call<Respond> postQuestion(@Body Question q) ;


    @POST("elkollya/vote/v.php")
    Call<Respond> upVote(@Body VoteRequest voteRequest);







}
