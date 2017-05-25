package com.projects.cactus.el_kollia.ApiServices;

import com.projects.cactus.el_kollia.model.*;

import java.util.List;
import com.projects.cactus.el_kollia.model.Question;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by el on 4/15/2017.
 */
public interface QuestionLoader {

    @POST("elkollya/load_question.php")
    Call<List<Question>> loadQuestions(@Body QuestionRequest questionRequest);
}
