package com.projects.cactus.el_kollia.feed.presenter;

import com.projects.cactus.el_kollia.model.Question;

import java.util.List;

/**
 * Created by el on 6/8/2017.
 */

public interface FeedContract  {


    void getPosts();
    void getSomePosts();
    void post(String userId,String post);
    void onPostRetrievedSuccess(List<Question> posts);
    void onPostRetrievedFailure(String erro);
    void refresh();
    void upvote(int questioId,String userId);
    void UpvotedSuccess();
    void upvotedFailure(String error);
    String getUserId(String key);

}
