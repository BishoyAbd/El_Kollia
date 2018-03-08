package com.projects.cactus.el_kollia.feed;

import com.projects.cactus.el_kollia.data.DataManager;
import com.projects.cactus.el_kollia.feed.model.FeedDataManager;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by el on 6/8/2017.
 */

public class FeedPresenter implements FeedContract.Presenter {

    private final DataManager dataManager;
    private FeedContract.View feedView;
    private FeedDataManager feedDataManager;
    private CompositeDisposable compositeDisposable;


    public FeedPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }


    @Override
    public void getPosts(String userId) {

    }

    @Override
    public void post(String userId, String post) {
        feedDataManager.post(userId, post);
    }

    @Override
    public void refresh() {

    }


    @Override
    public void upvote(int questioId, String userId) {
        feedDataManager.upVote(questioId, userId);

    }


    public String getUserId(String key) {
        return feedDataManager.getUserId(key);
    }

    @Override
    public void subscribe(FeedContract.View View) {
        this.feedView = feedView;
    }

    @Override
    public void unsubscribe() {

    }
}
