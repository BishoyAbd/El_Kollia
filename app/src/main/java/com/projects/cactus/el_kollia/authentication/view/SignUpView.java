package com.projects.cactus.el_kollia.authentication.view;

import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.User;

/**
 * Created by el on 6/7/2017.
 */

public interface SignUpView {

    void onSignUpSuccess(ServerResponse userId);
    void onSignUpError(String error);
    void showLoading();
    void hideLoading();


}
