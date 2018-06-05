package com.projects.cactus.el_kollia.application;

import android.content.Context;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Bishoy Abd on 3/30/2018.
 */

@Module(includes = ContextModule.class)
public class PicassoModule {


    @Provides
    Picasso providePicasso(Context context) {
        return new Picasso.Builder(context).build();

    }

//
//    @Provides
//    OkHttpDownloader provideOkHttpDownloader(OkHttpClient okHttpClient) {
//        return new Ok(okHttpClient);
//
//    }
}
