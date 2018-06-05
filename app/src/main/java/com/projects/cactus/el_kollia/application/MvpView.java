package com.projects.cactus.el_kollia.application;

import android.support.annotation.StringRes;

/**
 * Created by Bishoy Abd on 3/30/2018.
 */

public interface MvpView {


    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();

}

