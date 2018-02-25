package com.projects.cactus.el_kollia.feed.view;

import com.projects.cactus.el_kollia.model.Question;

import java.util.List;

/**
 * Created by el on 6/8/2017.
 */

public interface FeedView  {


    void showLoading();
    void hideLoading();
    void getAllPosts();
    void post(String userId,String post);
    void onPostRetrievedSuccess(List<Question> posts);
    void onPostRetrievdFailure(String error);
    void tryUpVote(int questionId,String userId);
    void upvotedSuccess();
    void upvotedFailure(String error);

}
