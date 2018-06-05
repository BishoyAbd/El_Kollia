package com.projects.cactus.el_kollia.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.projects.cactus.el_kollia.ApiServices.QuestionApiService;
import com.projects.cactus.el_kollia.BuildConfig;
import com.squareup.picasso.Picasso;

import timber.log.Timber;

/**
 * Created by Bishoy Abd on 3/26/2018.
 */

public class ElKollyaApp extends Application {


    private Picasso picasso;
    private QuestionApiService questionApiService;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        initializeTimber();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    void initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public QuestionApiService getQuestionApiService() {
        return questionApiService;
    }

    public Picasso getPicasso() {
        return picasso;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
