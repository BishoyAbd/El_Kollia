package com.projects.cactus.el_kollia.application;

import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by Bishoy Abd on 3/30/2018.
 */

@Module(includes = ContextModule.class)
public class NetworkModule {


    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor, Cache cache) {

        return new OkHttpClient.Builder().
                addInterceptor(interceptor)
                .cache(cache)
                .build();
    }


    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Timber.d(message));
    }


    @Provides
    public Cache provideCache(File file) {
        return new Cache(file, 10 * 1000 * 1000);
    }


    @Provides
    public File provideFile(Context context){
        return new File(context.getCacheDir(),"el_kollya_cache");

    }

}
