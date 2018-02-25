package com.projects.cactus.el_kollia.ApiServices;

import com.projects.cactus.el_kollia.model.Respond;
import com.projects.cactus.el_kollia.model.Vote;
import com.projects.cactus.el_kollia.model.VoteRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by el on 5/12/2017.
 */

public interface VoteApi {


    @POST("elkollya/vote/v.php")
    Call<Respond> upVote(@Body VoteRequest voteRequest);

    @POST("elkollya/")
    Call<Respond> downVote(@Body VoteRequest voteRequest);

    @POST("elkollya/vote/check_v.php")
    Call<Vote> alreadyUpVoted(@Body VoteRequest voteRequest);

}
