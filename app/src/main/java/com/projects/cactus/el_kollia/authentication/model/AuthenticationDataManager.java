package com.projects.cactus.el_kollia.authentication.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.projects.cactus.el_kollia.ApiServices.ServiceGenerator;
import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.User;
import com.projects.cactus.el_kollia.util.Util;

import io.reactivex.Single;
import timber.log.Timber;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by el on 6/7/2017.
 */

public class AuthenticationDataManager implements AuthenticationContract {


    public static final String LOG_PREF = "pref_log";
    public static final String KEY_LOG_SATE = "login_logout";
    private static AuthenticationDataManager instance;


    private SharedPreferences pref;
    private AuthenticationService authenticationService;


    private AuthenticationDataManager(Context context) {

        pref = context.getApplicationContext().getSharedPreferences(LOG_PREF, MODE_PRIVATE);
        authenticationService = ServiceGenerator.createService(AuthenticationService.class);


    }


    @Override
    public Single<ServerResponse> SignUpUer(User user) {

        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation(Util.REGISTER_OPERATION);
        serverRequest.setUser(user);

        return authenticationService.authenticate(serverRequest);

    }


    @Override
    public Single<ServerResponse> loginUser(String phone, String password) {

        Timber.d(Util.TAG, phone + "    " + password);

        AuthenticationService authenticationService = ServiceGenerator.createService(AuthenticationService.class);

        User user = new User();
        user.setPhoneNumber(phone);
        user.setPassword(password);

        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation(Util.LOGIN_OPERATION);
        serverRequest.setUser(user);

        return authenticationService.authenticate(serverRequest);

    }

    public Single<User> getUserInfo(String userId) {
        AuthenticationService authenticationService = ServiceGenerator.createService(AuthenticationService.class);
        return authenticationService.getUserData(userId);


    }


    @Override
    public boolean isLoggedin(String key_log_state, String key_user_id) {
        return pref.getBoolean(key_log_state, false) && pref.getString(key_user_id, Util.NO_USER_ID) != Util.NO_USER_ID;
    }

    @Override
    public String getUserId(String key) {
        return pref.getString(key, Util.NO_USER_ID);
    }

    @Override
    public void keepMeloggededIn(String keyLogedIn, String keyUser, String userId) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(keyLogedIn, true);
        editor.putString(keyUser, userId);
        editor.apply();
    }

    public void logOut(String key_user_login) {
        pref.edit().putBoolean(key_user_login, false).apply();

    }

    public static AuthenticationDataManager getInstance(Context context) {
        if (instance == null)
            instance = new AuthenticationDataManager(context);
        return instance;
    }
}
