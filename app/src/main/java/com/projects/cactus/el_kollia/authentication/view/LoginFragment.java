package com.projects.cactus.el_kollia.authentication.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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


import com.projects.cactus.el_kollia.authentication.presenter.LoginActivityPresenter;
import com.projects.cactus.el_kollia.feed.view.MainActivity;
import com.projects.cactus.el_kollia.BuildConfig;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.authentication.presenter.LoginPresenter;
import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.util.Util;

import static android.content.Context.MODE_PRIVATE;
import static com.projects.cactus.el_kollia.util.Util.KEY_LOGGED_IN;
import static com.projects.cactus.el_kollia.util.Util.KEY_USER_ID;

public class LoginFragment extends Fragment implements OnClickListener,LoginView {


    private  View view;
    private  EditText emailid, password;
    private  Button loginButton;
    private  TextView forgotPassword, signUp;
    private  CheckBox show_hide_password;
    private  LinearLayout loginLayout;
    private  Animation shakeAnimation;
    private  FragmentManager fragmentManager;

    private ProgressBar progress;
    private SharedPreferences pref;
    private LoginPresenter loginPresenter;

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

        if(BuildConfig.DEBUG) {
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
                loginPresenter = new LoginPresenter(this);
                loginPresenter.login(email,pass);
                break;

            case R.id.createAccount:
                // Replace signup frgament with animation
                replaceSignUpFragment();
                break;

        }

    }

    void replaceSignUpFragment(){

        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                .replace(R.id.frag_container, new SignupFragment(),
                        Util.SignUp_Fragment).commit();
    }



    private void openMainActivity(String unique_id) {
        Intent intent=new Intent(getActivity(),MainActivity.class);
        intent.putExtra(KEY_USER_ID,unique_id);
        startActivity(intent);
    }



    @Override
    public void onLoginSuccess(ServerResponse serverResponse) {
        keepMeLogedIn(Util.KEY_LOGGED_IN,Util.KEY_USER_ID,serverResponse.getUser().getUnique_id());
        openMainActivity(serverResponse.getUser().getUnique_id());

    }

    @Override
    public void onLoginFailure(String error) {
//error happened
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void keepMeLogedIn(String key, String userIdKey, String UserId) {
        new LoginPresenter(getActivity()).keepMeLogedIn(key, userIdKey, UserId);
    }
}