package com.projects.cactus.el_kollia.feed.presenter;

import android.content.Context;

import com.projects.cactus.el_kollia.feed.model.FeedDataManager;
import com.projects.cactus.el_kollia.feed.view.FeedFragment;
import com.projects.cactus.el_kollia.feed.view.FeedView;
import com.projects.cactus.el_kollia.model.Question;

import java.util.List;

/**
 * Created by el on 6/8/2017.
 */

public class FeedPresenter implements FeedContract {

    private FeedView feedView;
    private FeedDataManager feedDataManager;
    private Context context;


    public FeedPresenter(FeedView feedView) {
        this.feedView = feedView;
        feedDataManager=new FeedDataManager(this);

    }

    public FeedPresenter(Context context, FeedView feedFragment) {

        this.context = context;
        feedDataManager=new FeedDataManager(context,this);
        this.feedView=feedFragment;

    }


    @Override
    public void getPosts() {
        feedDataManager.getPosts();
    }

    @Override
    public void getSomePosts() {
//to be implemented
    }

    @Override
    public void post(String userId, String post) {
        feedDataManager.post(userId,post);
    }

    @Override
    public void onPostRetrievedSuccess(List<Question> posts) {
              feedView.onPostRetrievedSuccess(posts);
    }

    @Override
    public void onPostRetrievedFailure(String error) {

        feedView.onPostRetrievdFailure(error);
    }

    @Override
    public void refresh() {
         feedDataManager.getPosts();
    }

    @Override
    public void upvote(int questioId, String userId) {
        feedDataManager.upVote(questioId, userId);

    }

    @Override
    public void UpvotedSuccess() {
        feedView.upvotedSuccess();
    }

    @Override
    public void upvotedFailure(String error) {
          feedView.upvotedFailure(error);
    }

    @Override
    public String getUserId(String key) {
        return feedDataManager.getUserId(key);
    }
}
