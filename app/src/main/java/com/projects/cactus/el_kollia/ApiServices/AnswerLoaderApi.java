package com.projects.cactus.el_kollia.ApiServices;

import com.projects.cactus.el_kollia.model.Answer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by el on 5/14/2017.
 */

public interface AnswerLoaderApi {


    //field mus be used with form url encoded
    @POST("elkollya/load_answers.php")
    @FormUrlEncoded
    Call<List<Answer>> loadAnswers(@Field("question_id") String questionId);


    @POST("elkollya/post_answer.php")
    Call<List<Answer>> postAnswer(@Body Answer questionId);


}
