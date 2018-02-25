package com.projects.cactus.el_kollia.authentication.presenter;

import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.User;

/**
 * Created by el on 6/7/2017.
 */

public interface SignUpPresenterContract {

    void signUp(User user);
    void signUpnSuccess(ServerResponse userId);
    void signUpFailed(String error);
    void invalidCredentials();



}
