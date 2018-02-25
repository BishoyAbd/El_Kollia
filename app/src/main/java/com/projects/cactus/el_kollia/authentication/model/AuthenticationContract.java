package com.projects.cactus.el_kollia.authentication.model;

import android.content.Context;

/**
 * Created by el on 6/7/2017.
 */

interface AuthenticationContract {
    void SignUpUer(String name, String email, String pass, String confirmPass);
    void loginUser(String name,String password);
    boolean isLoggedin(String Key);
    String getUserId(String key);
    void keepMeloggededIn(String keyLogedIn,String keyUser,String userId);

}