package com.projects.cactus.el_kollia.authentication.presenter;


import com.projects.cactus.el_kollia.authentication.model.AuthenticationDataManager;
import com.projects.cactus.el_kollia.authentication.view.AuthActivityContract;
import com.projects.cactus.el_kollia.util.AppConstants;

/**
 * Created by el on 6/8/2017.
 */

public class AuthActivityPresenter implements AuthActivityContract.Presenter {

    private AuthenticationDataManager authenticationDataManager;

    private AuthActivityContract.View view;

    public AuthActivityPresenter(AuthActivityContract.View view, AuthenticationDataManager authenticationDataManager) {
        this.view = view;
        this.authenticationDataManager = authenticationDataManager;
    }

    public void keepMeLogedIn(String keyLogedIn, String keyUserId, String userId) {
        authenticationDataManager.keepMeloggededIn(keyLogedIn, keyUserId, userId);
    }


    public String getUserId(String key) {

        return authenticationDataManager.getUserId(key);
    }


    public void checkLogedIn() {
        if (authenticationDataManager.isLoggedin(AuthenticationDataManager.KEY_LOG_SATE, AppConstants.KEY_USER_ID))
            view.openProfileActivity(authenticationDataManager.getUserId(AppConstants.KEY_USER_ID));


        else
            view.replaceLoginFragment();

    }


}
