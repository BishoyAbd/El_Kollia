package com.projects.cactus.el_kollia.application;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Bishoy Abd on 3/30/2018.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }
}
