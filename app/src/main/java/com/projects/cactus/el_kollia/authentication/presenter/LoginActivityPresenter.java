package com.projects.cactus.el_kollia.authentication.presenter;

import android.content.Context;

import com.projects.cactus.el_kollia.authentication.model.AuthenticationDataManager;
import com.projects.cactus.el_kollia.feed.model.DataManagerContract;

/**
 * Created by el on 6/8/2017.
 */

public class LoginActivityPresenter {

    AuthenticationDataManager authenticationDataManager;
    Context context;

    public LoginActivityPresenter(Context context) {
        authenticationDataManager = new AuthenticationDataManager(context);
    }

   public void keepMeLogedIn(String keyLogedIn,String keyUserId,String userId){
        authenticationDataManager.keepMeloggededIn(keyLogedIn,keyUserId,userId);
    }



    public boolean isLogedIn(String key){

        return  authenticationDataManager.isLoggedin(key);

    }


    public String getUserId(String key){

       return authenticationDataManager.getUserId(key);
    }


}
