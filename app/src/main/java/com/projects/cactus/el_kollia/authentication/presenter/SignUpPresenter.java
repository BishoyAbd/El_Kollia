package com.projects.cactus.el_kollia.authentication.presenter;


import android.support.annotation.NonNull;

import com.projects.cactus.el_kollia.authentication.model.AuthenticationDataManager;
import com.projects.cactus.el_kollia.authentication.view.SignUpContract;
import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by el on 6/7/2017.
 */

public class SignUpPresenter implements SignUpContract.Presenter {

    private AuthenticationDataManager authenticationDataManager;
    private SignUpContract.SignUpView signUpView;
    private CompositeDisposable compositeDisposable;


    public SignUpPresenter(SignUpContract.SignUpView signUpView, AuthenticationDataManager authenticationDataManager) {
        this.signUpView = signUpView;
        this.authenticationDataManager = authenticationDataManager;
        compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void signUp(User user) {
        signUpView.showLoading();

        compositeDisposable.clear();
        Disposable disposable =
                authenticationDataManager.SignUpUer(user)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ServerResponse>() {

                            @Override
                            public void onSuccess(@NonNull ServerResponse serverResponse) {
                                signUpView.hideLoading();
                                signUpView.hideError();
                                process(serverResponse);

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                signUpView.showError();
                                signUpView.hideLoading();
                            }
                        });


        compositeDisposable.add(disposable);

    }


    private void process(ServerResponse serverResponse) {
//        //error =false ----- response will contain a user and a message
//        //error =true  -------response will contain only message to indicate what happened
//        if (!Boolean.parseBoolean(serverResponse.getError())) {
//            signUpView.hideError();
//            signUpView.hideLoading();
//            signUpView.onSignUpSuccess(serverResponse.getUser());
//            Timber.d("message recieved with no error from server---> " + serverResponse.getMessage());
//        }
//        //error sent from server
//        else
//            signUpView.showMessage(serverResponse.getMessage());
//        Timber.d("message recieved with error from server---> " + serverResponse.getMessage());


    }


    @Override
    public void subscribe(SignUpContract.SignUpView view) {
        this.signUpView = view;
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }
}

