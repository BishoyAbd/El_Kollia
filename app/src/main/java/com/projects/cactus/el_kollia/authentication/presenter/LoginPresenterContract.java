package com.projects.cactus.el_kollia.authentication.presenter;

import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.User;

/**
 * Created by el on 6/8/2017.
 */

public interface LoginPresenterContract {


    void login(String userName,String password);
    void loginSuccess(ServerResponse userId);
    void loginFailed(String error);
    void invalidLoginCredentials();
    void keepMeLogedIn(String Key,String userIdKey,String UserId);
}
