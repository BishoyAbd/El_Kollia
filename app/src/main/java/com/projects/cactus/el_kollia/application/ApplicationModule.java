package com.projects.cactus.el_kollia.application;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Bishoy Abd on 3/30/2018.
 */

@Module(includes = {QuestionApiModule.class})
public class ApplicationModule {


    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context context() {
        return mApplication;
    }

    @Provides
    Application application() {
        return mApplication;
    }


//    @Provides
//    @PreferenceInfo
//    String providePreferenceName() {
//        return AppConstants.PREF_NAME;
//    }


}
