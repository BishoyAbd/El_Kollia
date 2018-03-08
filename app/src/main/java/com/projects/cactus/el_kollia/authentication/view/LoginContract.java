package com.projects.cactus.el_kollia.authentication.view;


import com.projects.cactus.el_kollia.model.User;

/**
 * Created by el on 6/8/2017.
 */

public interface LoginContract {


    interface LoginView {

        void setPresenter(Presenter presenter);

        void onLoginSuccess(User user);


        void showMessage(String message);

        void enableInput(boolean b);

        void showLoading();

        void hideLoading();

        void showError();

        void hideError();
    }


    interface Presenter {
        void login(String phone, String pass);

        boolean isLoggedIn(String keyLogSate, String keyUserId);

        void subscribe(LoginContract.LoginView view);

        void unsubscribe();
    }

}
