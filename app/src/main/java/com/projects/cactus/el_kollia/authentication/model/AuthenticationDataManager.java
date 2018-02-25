package com.projects.cactus.el_kollia.authentication.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.projects.cactus.el_kollia.ApiServices.ServiceGenerator;
import com.projects.cactus.el_kollia.authentication.presenter.LoginPresenterContract;
import com.projects.cactus.el_kollia.authentication.presenter.SignUpPresenterContract;
import com.projects.cactus.el_kollia.model.ServerRequest;
import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.User;
import com.projects.cactus.el_kollia.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by el on 6/7/2017.
 */

public class AuthenticationDataManager implements AuthenticationContract {


    private SignUpPresenterContract signUpPresenterContract;
    private LoginPresenterContract presenterContract;
    private Context context;
    SharedPreferences  pref;

    public AuthenticationDataManager(Context context) {
        this.context=context;
        pref = context.getApplicationContext().getSharedPreferences(Util.LOG_PREF,MODE_PRIVATE);

    }

    public AuthenticationDataManager(SignUpPresenterContract signUpPresenterContract) {

        this.signUpPresenterContract = signUpPresenterContract;
    }

    public AuthenticationDataManager(LoginPresenterContract presenterContract) {

        this.presenterContract = presenterContract;
    }

    @Override
    public void SignUpUer(String name, String email, String pass, String confirmPass) {

        AuthenticationService authenticationService = ServiceGenerator.createService(AuthenticationService.class);

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setAcademic_year("second");
        user.setDepartment("cs");
        user.setClassification(1);
        user.setPhoneNumber("01228790902");
        user.setPassword(pass);

        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation(Util.REGISTER_OPERATION);
        serverRequest.setUser(user);


        Call<ServerResponse> call = authenticationService.authenticate(serverRequest);
        call.enqueue(new Callback<ServerResponse>() {

            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                signUpPresenterContract.signUpnSuccess(resp);
                // Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
                //  ((WelcomeActivity) getActivity()).replaceLoginFragment();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                signUpPresenterContract.signUpFailed(t.getLocalizedMessage());
            }
        });


    }

    @Override
    public void loginUser(String email, String password) {

        Log.e(Util.TAG, email + "    " + password);

        AuthenticationService authenticationService = ServiceGenerator.createService(AuthenticationService.class);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation(Util.LOGIN_OPERATION);
        serverRequest.setUser(user);

        Call<ServerResponse> call = authenticationService.authenticate(serverRequest);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp = response.body();
                if (resp.getResult().equals(Util.SUCCESS)) {
                    presenterContract.loginSuccess(resp);


                }

            }


            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Util.TAG, "failed");
                presenterContract.loginFailed(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public boolean isLoggedin(String key) {
        return pref.getBoolean(key,false);
    }

    @Override
    public String getUserId(String key) {
        return pref.getString(key,"no_user");
    }

    @Override
    public void keepMeloggededIn(String keyLogedIn,String keyUser,String userId) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(keyLogedIn, true);
        editor.putString(keyUser,userId);
        editor.apply();
    }


}
