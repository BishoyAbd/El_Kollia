package com.projects.cactus.el_kollia.authentication.presenter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.projects.cactus.el_kollia.authentication.model.AuthenticationDataManager;
import com.projects.cactus.el_kollia.authentication.view.LoginView;
import com.projects.cactus.el_kollia.model.ServerResponse;

/**
 * Created by el on 6/8/2017.
 */

public class LoginPresenter implements LoginPresenterContract {

    private LoginView loginView;
    private AuthenticationDataManager authenticationDataManager;
    AuthenticationInteractor authenticationInteractor;
    private Context context;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public LoginPresenter(Context context) {
        this.context = context;
    }


    @Override
    public void login(String userName, String password) {
        authenticationInteractor=new AuthenticationInteractor();
        if (authenticationInteractor.checkValidation(userName,password))     {
            authenticationDataManager=new AuthenticationDataManager(this);
            authenticationDataManager.loginUser(userName,password);
        }
        else
            invalidLoginCredentials();


    }

    @Override
    public void loginSuccess(ServerResponse serverResponse) {
        loginView.onLoginSuccess(serverResponse);
    }

    @Override
    public void loginFailed(String error) {
        loginView.onLoginFailure(error);
    }

    @Override
    public void invalidLoginCredentials() {
        loginView.onLoginFailure(AuthenticationInteractor.ERROR_INVALID_CRED);
    }

    @Override
    public void keepMeLogedIn(String Key, String userIdKey, String UserId) {
           new AuthenticationDataManager(context).keepMeloggededIn(Key, userIdKey, UserId);
    }
}
