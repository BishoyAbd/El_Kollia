package com.projects.cactus.el_kollia.base;

/**
 * Created by Bishoy Abd on 2/24/2018.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);
    void showLoading();
    void hideLoading();
    void showError();
    void hideError();


}
