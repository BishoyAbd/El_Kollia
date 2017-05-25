package com.projects.cactus.el_kollia.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.util.Util;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private String TAG="LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        pref=getSharedPreferences(Util.LOG_PREF,MODE_PRIVATE);


        //pref = getPreferences(0);

        Log.d(TAG,"is loged in ---> "+pref.getBoolean(Util.IS_LOGGED_IN,false));
        if (pref.getBoolean(Util.IS_LOGGED_IN,false)){
            startActivity(new Intent(this,MainActivity.class));
                  finish();

        }
        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frag_container, new LoginFragment(),
                            Util.Login_Fragment).commit();
        }



    }

    // Replace Login Fragment with animation
    protected void replaceLoginFragment() {

       getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frag_container, new LoginFragment(),
                        Util.Login_Fragment).commit();
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
