package com.projects.cactus.el_kollia.authentication.view;

import com.projects.cactus.el_kollia.model.ServerResponse;

/**
 * Created by el on 6/8/2017.
 */

public interface LoginView {

    void onLoginSuccess(ServerResponse serverResponse);
    void onLoginFailure(String error);
    void showLoading();
    void hideLoading();
    void keepMeLogedIn(String Key,String userIdKey,String UserId);

}
