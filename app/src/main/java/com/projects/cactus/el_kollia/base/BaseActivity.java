package com.projects.cactus.el_kollia.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.projects.cactus.el_kollia.R;

import static com.projects.cactus.el_kollia.util.AppConstants.EXTRA_ARGUMENTS;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Bundle activityArguments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initSupportActionBar();
        initArguments(savedInstanceState);
        initPresenter();
        initActivity();
    }

    /**
     * Toolbar will be configured like a {@link ActionBar} if exists in the layout
     * if it doesn't exist will be ignored
     */

    private void initSupportActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            onSetupSupportActionBar(getSupportActionBar());
        }
    }

    /**
     * Called just after bindViews.
     * Override this method to configure your {@link ActionBar}
     */

    protected void onSetupSupportActionBar(ActionBar actionBar) {

    }

    /**
     * Called after to start ui state.
     * Override this method to configure your presenter with extra data if needed.
     */

    protected void initArguments(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(EXTRA_ARGUMENTS)) {
            activityArguments = savedInstanceState.getBundle(EXTRA_ARGUMENTS);
        } else if (getIntent().getExtras() != null) {
            activityArguments = getIntent().getExtras().getBundle(EXTRA_ARGUMENTS);
        }
    }

    /**
     * Called before to start all the presenter instances linked to the component lifecycle.
     * Override this method to configure your presenter with extra data if needed.
     */

    protected void initPresenter() {

    }

    /**
     * Called just after setContentView.
     * Override this method to configure your activity or start views
     */

    protected void initActivity() {

    }

    /**
     * @return Toolbar if you need configure directly the toolbar
     */

    @Nullable
    public Toolbar getBaseToolbar() {
        return toolbar;
    }

    /**
     * @return the layout id associated to the layout used in the activity.
     */

    @LayoutRes
    protected abstract int getLayoutResId();

    public Bundle getActivityArguments() {
        return activityArguments;
    }


}