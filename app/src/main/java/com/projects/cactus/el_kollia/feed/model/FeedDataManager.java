package com.projects.cactus.el_kollia.feed.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.projects.cactus.el_kollia.ApiServices.QuestionLoader;
import com.projects.cactus.el_kollia.ApiServices.QuestionPost;
import com.projects.cactus.el_kollia.ApiServices.ServiceGenerator;
import com.projects.cactus.el_kollia.ApiServices.VoteApi;
import com.projects.cactus.el_kollia.feed.presenter.FeedContract;
import com.projects.cactus.el_kollia.feed.presenter.FeedPresenter;
import com.projects.cactus.el_kollia.feed.view.MainActivity;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.QuestionRequest;
import com.projects.cactus.el_kollia.model.Respond;
import com.projects.cactus.el_kollia.model.VoteRequest;
import com.projects.cactus.el_kollia.util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by el on 6/8/2017.
 */

public class FeedDataManager implements DataManagerContract{


    private static final String TAG = "DataManagerContract";
    private FeedContract feedPresenter;
    private Context context;
    SharedPreferences pref;

    public FeedDataManager(FeedContract feedPresenter) {

        this.feedPresenter = feedPresenter;
    }

    public FeedDataManager(Context context, FeedPresenter feedPresenter) {
        this.context = context;
        this.feedPresenter = feedPresenter;
        if (context==null)
            Log.d(TAG,"context is null");
           pref= context.getApplicationContext().getSharedPreferences(Util.LOG_PREF,Context.MODE_PRIVATE);

    }

    @Override
    public void getPosts() {
        //to be added to the questionRequest to get specific posts
        String year;
        int subjectId;
        QuestionRequest questionRequest = new QuestionRequest();
        QuestionLoader questionLoader = ServiceGenerator.createService(QuestionLoader.class);
        Call<List<Question>> call = questionLoader.loadQuestions(questionRequest);
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                   feedPresenter.onPostRetrievedSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                      feedPresenter.onPostRetrievedFailure(t.getLocalizedMessage());
                         Log.d(TAG,t.getLocalizedMessage());
            }
        });



    }

    @Override
    public void getSomePosts() {
    }

    @Override
    public void post(String userId, String post) {
        Question question=new Question();
        question.setAcademic_year(0);
        question.setUser_id(MainActivity.getUnique_id());
        question.setCourse("java");
        question.setQuestion(post);

        QuestionPost questionPost = ServiceGenerator.createService(QuestionPost.class);

        Call<Respond> call = questionPost.postQuestion(question);

        call.enqueue(new Callback<Respond>() {
            @Override
            public void onResponse(Call<Respond> call, Response<Respond> response) {
            }

            @Override
            public void onFailure(Call<Respond> call, Throwable t) {
            }
        });
        }

    @Override
    public boolean isUpVoted(String userId, String postId) {
        return false;
    }

    @Override
    public boolean upVote(int questionId, String userId) {

        Question question=new Question();
        question.setUser_id(userId);
        question.setId(questionId);

        VoteRequest voteRequest=new VoteRequest();
        voteRequest.setQuestion(question);
        voteRequest.setCommand(VoteRequest.INCREASE_UP_VOTES);
        voteRequest.setCode("ss");
        VoteApi questionPost=ServiceGenerator.createService(VoteApi.class);
        Call<Respond> call=questionPost.upVote(voteRequest);
        call.enqueue(new Callback<Respond>() {
            @Override
            public void onResponse(Call<Respond> call, Response<Respond> response) {
                Log.d(TAG,response.body().getMessage());
                feedPresenter.UpvotedSuccess();
            }

            @Override
            public void onFailure(Call<Respond> call, Throwable t) {
     //           Log.d(TAG,t.getLocalizedMessage());
                   feedPresenter.upvotedFailure(t.getLocalizedMessage());
            }
        });


        return true;
    }

    @Override
    public String getUserId(String key) {
        String userId=pref.getString(key,Util.ERROR_ID_INVALIDE);
        Log.d(TAG, "user_id from pref ---> "+userId);
        return userId;
    }
}




//to solve the upvote problem
//solution 1
//get all the userIds who upvoted along with every post
//and search for this if the user id contained in the list make it green
//sole 2 is the one ur using currently
