package com.projects.cactus.el_kollia.base;

import android.app.ProgressDialog;
import android.view.View;

/**
 * Created by Bishoy Abd on 6/1/2018.
 */
public abstract class ErrorLoadingActivity extends BaseActivity implements BaseView {

    private ProgressDialog progressDialog;
    private View errorView;

    @Override
    protected void initActivity() {
        super.initActivity();
        errorView = findViewById(getErrorViewResId());
        progressDialog = new ProgressDialog(this);
    }

    protected abstract int getErrorViewResId();

    @Override
    public void setPresenter(Object presenter) {

    }


    @Override
    public void showLoading() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog.isShowing())
            progressDialog.hide();
    }

    @Override
    public void showError() {
        errorView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideError() {
        errorView.setVisibility(View.INVISIBLE);
    }

}
