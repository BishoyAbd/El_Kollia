package com.projects.cactus.el_kollia.authentication.presenter;

import com.projects.cactus.el_kollia.authentication.model.AuthenticationDataManager;
import com.projects.cactus.el_kollia.authentication.view.LoginContract;
import com.projects.cactus.el_kollia.model.ServerResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by el on 6/8/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.LoginView loginView;
    private AuthenticationDataManager authenticationDataManager;
    private CompositeDisposable compositeDisposable;

    public LoginPresenter(LoginContract.LoginView loginView, AuthenticationDataManager authenticationDataManager) {
        this.loginView = loginView;
        this.authenticationDataManager = authenticationDataManager;
        compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void login(String userName, String password) {

        loginView.showLoading();
        loginView.enableInput(false);

        compositeDisposable.clear();
        Disposable disposable = authenticationDataManager.loginUser(userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ServerResponse>() {
                    @Override
                    public void onSuccess(@NonNull ServerResponse serverResponse) {
                        process(serverResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loginView.hideLoading(); //reset btn  inside it
                        Timber.d("button was reset to it's first state");

                        //error while conecting
                        loginView.enableInput(true);
                        loginView.showError();
                    }
                });

        compositeDisposable.add(disposable);

    }

    @Override
    public boolean isLoggedIn(String keyLogSate, String keyUserId) {
        return authenticationDataManager.isLoggedin(keyLogSate, keyUserId);
    }

    private void process(ServerResponse serverResponse) {
        loginView.hideLoading(); //reset button inside it
        loginView.enableInput(true);
        Timber.d("button was reset to it's first state");
        loginView.hideError();
        //if no error from server
        if (!Boolean.parseBoolean(serverResponse.getError())) {
            loginView.onLoginSuccess(serverResponse.getUser());

        }

        //error from server ... either a prloblem or user doesnot exist
        else
            loginView.showMessage(serverResponse.getMessage());


    }


    public void keepMeLoggedIn(String Key, String userIdKey, String UserId) {
        authenticationDataManager.keepMeloggededIn(Key, userIdKey, UserId);
    }

    @Override
    public void subscribe(LoginContract.LoginView view) {
        this.loginView = view;
    }

    @Override
    public void unsubscribe() {
//        loginView = null;
        compositeDisposable.clear();
    }
}
