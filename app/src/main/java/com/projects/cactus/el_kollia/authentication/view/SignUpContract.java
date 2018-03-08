package com.projects.cactus.el_kollia.authentication.view;


import com.projects.cactus.el_kollia.model.User;

/**
 * Created by el on 6/7/2017.
 */

public interface SignUpContract {


    interface SignUpView {

        void setPresenter(LoginContract.Presenter presenter);

        void onSignUpSuccess(User userId);

        void showMessage(String message);

        void showLoading();

        void hideLoading();

        void hideError();

        void showError();
    }


    interface Presenter {
        void signUp(User user);

        void subscribe(SignUpView view);

        void unsubscribe();
    }

}
