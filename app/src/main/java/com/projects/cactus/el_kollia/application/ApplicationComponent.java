package com.projects.cactus.el_kollia.application;

import com.projects.cactus.el_kollia.ApiServices.QuestionApiService;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Bishoy Abd on 3/30/2018.
 */

@Singleton
@Component(modules ={ ApplicationModule.class,PicassoModule.class})
public interface ApplicationComponent {

    Picasso picasso();
    QuestionApiService questionApiService();

}