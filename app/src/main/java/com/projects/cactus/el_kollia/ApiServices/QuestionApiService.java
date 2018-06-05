package com.projects.cactus.el_kollia.ApiServices;

import com.projects.cactus.el_kollia.model.LikeRequest;
import com.projects.cactus.el_kollia.model.LikeResponse;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.QuestionRequest;
import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.Vote;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by el on 4/15/2017.
 */
public interface QuestionApiService {

//    @POST("elkollya/load_question.php")
//    Call<List<Question>> loadQuestions(@Body QuestionRequest questionRequest);

    @POST("elkollya/questions.php")
    Observable<List<Question>> loadQuestions(@Body QuestionRequest questionRequest);


    @POST("elkollya/load_user_questions.php")
    Call<List<Question>> loadUsrtQuestions(@Body QuestionRequest questionRequest);


    @POST("elkollya/vote/v.php")
    Observable<LikeResponse> upVote(@Body LikeRequest likeRequest);

    @POST("elkollya/")
    Observable<ServerResponse> downVote(@Body LikeRequest likeRequest); //past return was respond

    @POST("elkollya/vote/check_v.php")
    Call<Vote> alreadyUpVoted(@Body LikeRequest likeRequest);


    @POST("elkollya/post_question.php")
    Observable<ServerResponse> postQuestion(@Body QuestionRequest questionRequest); //past was question


}
