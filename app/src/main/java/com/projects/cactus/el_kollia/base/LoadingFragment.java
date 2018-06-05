package com.projects.cactus.el_kollia.base;

import android.support.annotation.NonNull;
import android.view.View;

import butterknife.Unbinder;

/**
 * Created by Bishoy Abd on 5/31/2018.
 */
public class LoadingFragment extends BaseFragment implements BaseView {

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected Unbinder getUnBinder() {
        return null;
    }

    @Override
    public void setPresenter(Object presenter) {

    }


    @Override
    protected void initFragment(@NonNull View view) {
        super.initFragment(view);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void hideError() {

    }
}
