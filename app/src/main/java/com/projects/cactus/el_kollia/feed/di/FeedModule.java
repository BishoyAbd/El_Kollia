package com.projects.cactus.el_kollia.feed.di;

import android.content.Context;

import com.projects.cactus.el_kollia.Activity.MainActivity;
import com.projects.cactus.el_kollia.ApiServices.QuestionApiService;
import com.projects.cactus.el_kollia.data.LocalAuthStore;
import com.projects.cactus.el_kollia.feed.FeedContract;
import com.projects.cactus.el_kollia.feed.FeedInteractor;
import com.projects.cactus.el_kollia.feed.FeedInteractorImp;
import com.projects.cactus.el_kollia.feed.FeedPresenter;
import com.projects.cactus.el_kollia.feed.adapter.FeedAdapterContract;
import com.projects.cactus.el_kollia.feed.adapter.FeedAdapterPresenter;
import com.projects.cactus.el_kollia.like.LikeInteractor;
import com.projects.cactus.el_kollia.like.LikeInteractorContract;
import com.projects.cactus.el_kollia.util.rx.AppSchedulerProvider;
import com.projects.cactus.el_kollia.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Bishoy Abd on 3/30/2018.
 */

@Module
public class FeedModule {

    private MainActivity mActivity;


    public FeedModule(MainActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    @FeedScope
    Context provideContext() {
        return mActivity;
    }

    @Provides
    @FeedScope
    MainActivity provideActivity() {
        return mActivity;
    }


    @Provides
    @FeedScope
    FeedContract.Presenter provideFeedPresenter(FeedInteractor feedInteractor, SchedulerProvider schedulerProvider) {
        return new FeedPresenter(feedInteractor, schedulerProvider);

    }

    @Provides
    @FeedScope
    FeedAdapterContract.Presenter provideFeedAdapterPresenter(LikeInteractorContract likeInteractor) {
        return new FeedAdapterPresenter(likeInteractor);

    }

    @Provides
    @FeedScope
    FeedInteractor provideFeedInteractor(QuestionApiService questionApiService, LocalAuthStore localAuthStore) {
        return new FeedInteractorImp(questionApiService, localAuthStore);
    }


    @Provides
    @FeedScope
    LocalAuthStore provideLocalAuthStore() {
        return new LocalAuthStore(mActivity);
    }


    @Provides
    @FeedScope
    LikeInteractorContract provideLikeInteractor(QuestionApiService service) {
        return new LikeInteractor(service);
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


}
