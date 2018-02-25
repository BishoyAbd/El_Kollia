package com.projects.cactus.el_kollia.authentication.presenter;

import com.projects.cactus.el_kollia.authentication.view.SignUpView;
import com.projects.cactus.el_kollia.authentication.model.AuthenticationDataManager;
import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.User;

/**
 * Created by el on 6/7/2017.
 */

public class SignUpPresenter implements SignUpPresenterContract {

    private AuthenticationDataManager authenticationDataManager;
    private SignUpView signUpView;
    private AuthenticationInteractor authenticationInteractor;

    public SignUpPresenter(SignUpView signUpView) {
        this.signUpView = signUpView;

    }


    @Override
    public void signUp(User user) {
        authenticationInteractor =new AuthenticationInteractor();
        if (authenticationInteractor.checkValidation(user.getName(),user.getEmail(),user.getPassword(),user.getConfirmPassword())){
            authenticationDataManager=new AuthenticationDataManager(this);
            authenticationDataManager.SignUpUer(user.getName(),user.getEmail(),user.getPassword(),user.getConfirmPassword());
        }
        else
            invalidCredentials();


    }


    @Override
    public void signUpnSuccess(ServerResponse serverResponse) {
        signUpView.onSignUpSuccess(serverResponse);
    }

    @Override
    public void signUpFailed(String error) {
          signUpView.onSignUpError(error);
    }

    @Override
    public void invalidCredentials() {
        signUpView.onSignUpError(authenticationInteractor.ERROR_INVALID_CRED);
    }

}
