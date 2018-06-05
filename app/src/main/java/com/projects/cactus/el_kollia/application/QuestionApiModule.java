package com.projects.cactus.el_kollia.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.projects.cactus.el_kollia.ApiServices.QuestionApiService;
import com.projects.cactus.el_kollia.util.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bishoy Abd on 3/30/2018.
 */

@Module(includes = NetworkModule.class)
public class QuestionApiModule {


    @Provides
    @Singleton
    public QuestionApiService provideQuestionApiService(Retrofit retrofit) {
        return retrofit.create(QuestionApiService.class);
    }


    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();


    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient, @Url String url) {

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .client(okHttpClient)
                .build();
    }

    @Url
    @Provides
    public String provideURL() {
        return AppConstants.BASE_URL;
    }


    //I need client , gson so I will create a sepearte module just for redability

}
