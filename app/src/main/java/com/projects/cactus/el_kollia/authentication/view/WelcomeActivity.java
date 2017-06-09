package com.projects.cactus.el_kollia.authentication.view;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.projects.cactus.el_kollia.authentication.presenter.LoginActivityPresenter;
import com.projects.cactus.el_kollia.feed.view.MainActivity;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.util.Util;

import static com.projects.cactus.el_kollia.util.Util.KEY_LOGGED_IN;
import static com.projects.cactus.el_kollia.util.Util.KEY_USER_ID;
import static com.projects.cactus.el_kollia.util.Util.NO_USER_ID;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {


    Button newAccount_btn, alreadyUser_btn;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private LoginActivityPresenter loginActivityPresenter;
    private String userId;
    private String TAG = "WelcomeActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //sharedPreferences = getPreferences(0);
        loginActivityPresenter = new LoginActivityPresenter(this);
        userId = loginActivityPresenter.getUserId(Util.KEY_USER_ID);

        Log.d(TAG,"user id ---> "+userId);
        Log.d(TAG,"is loged in ---> "+loginActivityPresenter.isLogedIn(KEY_LOGGED_IN));
        if (loginActivityPresenter.isLogedIn(KEY_LOGGED_IN)&&userId!=NO_USER_ID) {
            Intent intent=new Intent(this, MainActivity.class);
            intent.putExtra(KEY_USER_ID,userId);
            startActivity(intent);
            finish();

        }
           initializeViews();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.already_user_btn_id:
                replaceLoginFragment();
                break;
            case R.id.create_account_btn_id:
                replaceSignUpFragment();
                break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        attachListeners();

    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    void replaceLoginFragment() {

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_container, new LoginFragment(), Util.Login_Fragment).setCustomAnimations(R.anim.right_enter, R.anim.right_out);
        fragmentTransaction.commit();

    }


    void replaceSignUpFragment() {

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frag_container, new SignupFragment(), Util.SignUp_Fragment).setCustomAnimations(R.anim.left_enter, R.anim.left_out);
        fragmentTransaction.commit();
    }

    void initializeViews() {


        alreadyUser_btn = (Button) findViewById(R.id.already_user_btn_id);
        newAccount_btn = (Button) findViewById(R.id.create_account_btn_id);

    }


    void attachListeners() {
        alreadyUser_btn.setOnClickListener(this);
        newAccount_btn.setOnClickListener(this);

    }


    @Override
    public void onBackPressed() {

        // Find the tag of signup and forgot password fragment
        Fragment SignUp_Fragment = getSupportFragmentManager()
                .findFragmentByTag(Util.SignUp_Fragment);
        Fragment ForgotPassword_Fragment = getSupportFragmentManager()
                .findFragmentByTag(Util.ForgotPassword_Fragment);

        // Check if both are null or not
        // If both are not null then replace login fragment else do backpressed
        // task

        if (SignUp_Fragment != null)
            replaceLoginFragment();
        else if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }

}
