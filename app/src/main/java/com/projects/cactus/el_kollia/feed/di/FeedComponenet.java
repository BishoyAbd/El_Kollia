package com.projects.cactus.el_kollia.feed.di;

import com.projects.cactus.el_kollia.Activity.MainActivity;
import com.projects.cactus.el_kollia.application.ApplicationComponent;
import com.projects.cactus.el_kollia.feed.FeedFragment;

import dagger.Component;

/**
 * Created by Bishoy Abd on 4/2/2018.
 */

@FeedScope
@Component(modules = FeedModule.class, dependencies = ApplicationComponent.class)
public interface FeedComponenet {

    void inject(FeedFragment feedFragment);

    void inject(MainActivity activity);
}
