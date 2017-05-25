package com.projects.cactus.el_kollia.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.projects.cactus.el_kollia.ApiServices.AuthenticationService;
import com.projects.cactus.el_kollia.ApiServices.ServiceGenerator;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.customviews.CustomToast;
import com.projects.cactus.el_kollia.model.ServerRequest;
import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.User;
import com.projects.cactus.el_kollia.util.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment implements OnClickListener {
    private static View view;

    private static EditText emailid, password;
    private static Button loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    private ProgressBar progress;
    private SharedPreferences pref;
    private boolean debugging=true;

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);


        initViews();
        setListeners();
        return view;
    }

    // Initiate Views
    private void initViews() {

       // pref = getActivity().getPreferences(0);
        pref=getActivity().getSharedPreferences(Util.LOG_PREF,MODE_PRIVATE);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        fragmentManager = getActivity().getSupportFragmentManager();

        emailid = (EditText) view.findViewById(R.id.login_emailid);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
        forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        signUp = (TextView) view.findViewById(R.id.createAccount);
        show_hide_password = (CheckBox) view
                .findViewById(R.id.show_hide_password);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),R.anim.shake);

        if(debugging) {
            emailid.setText("qqq@el-eng.menoufia.edu.eg");
            password.setText("qqq");
        }
    }

    // Set Listeners
    private void setListeners() {

        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);

        // Set check listener over checkbox for showing and hiding password
        show_hide_password
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                String email=emailid.getText().toString();
                String pass=password.getText().toString();
                if(checkValidation(email,pass)){

                    doLoginOperation(email,pass);
                }
                break;

            case R.id.createAccount:
                // Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frag_container, new SignupFragment(),
                                Util.SignUp_Fragment).commit();
                break;
        }

    }

    private void doLoginOperation(String email,String password) {


        Log.e(Util.TAG,email+"    "+password);

        AuthenticationService authenticationService= ServiceGenerator.createService(AuthenticationService.class);

        User user=new User();
        user.setEmail(email);
        user.setPassword(password);

        ServerRequest serverRequest=new ServerRequest();
        serverRequest.setOperation(Util.LOGIN_OPERATION);
        serverRequest.setUser(user);

        Call<ServerResponse> call=authenticationService.authenticate(serverRequest);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
                if(resp.getResult().equals(Util.SUCCESS)){
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean(Util.IS_LOGGED_IN,true);
                    editor.putString(Util.NAME,resp.getUser().getName());
                    editor.putString(Util.UNIQUE_ID,resp.getUser().getUnique_id());
                    editor.apply();
                    openMainActivity(resp.getUser().getUnique_id());

                }
                progress.setVisibility(View.INVISIBLE);

            }

            

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                progress.setVisibility(View.INVISIBLE);
                Log.d(Util.TAG,"failed");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void openMainActivity(String unique_id) {

        Intent intent=new Intent(getActivity(),MainActivity.class);
        intent.putExtra(Util.EXTRA_UNIQUE_ID,unique_id);
        startActivity(intent);
    }

    // Check Validation before login
    private boolean checkValidation(String email, String pass) {
        boolean valid;
        // Get email id and password

        // Check patter for email id
        Pattern p = Pattern.compile(Util.regEx_email);

        Matcher m = p.matcher(email);

        // Check for both field is empty or not
        if (email.equals("") || email.length() == 0
                || pass.equals("") || pass.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "Enter both credentials.");
            valid=false;

        }
        // Check if email id is valid or not
        else if (!m.find()) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");
            valid = false;
        }
            // Else do login and do your stuff
        else{
//            Toast.makeText(getActivity(), "Do Login.", Toast.LENGTH_SHORT)
//                    .show();
valid=true;
        }
        return  valid;

    }
}