package com.projects.cactus.el_kollia.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.projects.cactus.el_kollia.authentication.model.AuthenticationService;
import com.projects.cactus.el_kollia.ApiServices.ServiceGenerator;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.authentication.view.WelcomeActivity;
import com.projects.cactus.el_kollia.model.User;
import com.projects.cactus.el_kollia.util.Util;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by el on 4/15/2017.
 */

public class ProfileFragment extends Fragment {

    SharedPreferences sharedPreferences;

    String email;
    String password;
    private User user;

    //or User user;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile2, container, false);
       sharedPreferences=getActivity().getSharedPreferences(Util.LOG_PREF,MODE_PRIVATE);

        // sharedPreferences = getActivity().getPreferences(0);
        Glide.with(getActivity()).load(R.drawable.profile_bisho).into((ImageView) v.findViewById(R.id.user_profile_photo));

        Button logOut_btn = (Button) v.findViewById(R.id.logOutBtn);

        logOut_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sharedPreferences.edit().
                        putBoolean(Util.IS_LOGGED_IN, false)
                        .apply();
                startActivity(new Intent(getActivity(), WelcomeActivity.class));
                getActivity().finish();
            }
        });

        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getProfileInfo(email,password);
        //      getUserPosts(email,password);
    }

    private void getProfileInfo(String email, String password) {
        AuthenticationService authenticationService = ServiceGenerator.createService(AuthenticationService.class);

        RequestBody emailReqBody = ServiceGenerator.createFromString(email);
        RequestBody passReqBody = ServiceGenerator.createFromString(password);
        Call<User> call = authenticationService.getUserData(emailReqBody, passReqBody);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("ProfileFragment", "on response is called ");
                user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ProfileFragment", "on error is called ");
            }
        });


    }


    void getUserPosts(String email, String password) {
        //don't forget back stack

    }


}
