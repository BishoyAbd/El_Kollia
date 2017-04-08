package com.projects.cactus.el_kollia.Activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.projects.el.miniawi.R;
import com.projects.el.miniawi.activities.ApiServices.AuthenticationService;
import com.projects.el.miniawi.activities.ApiServices.ServiceGenerator;
import com.projects.el.miniawi.activities.customviews.CustomToast;
import com.projects.el.miniawi.activities.model.ServerRequest;
import com.projects.el.miniawi.activities.model.ServerResponse;
import com.projects.el.miniawi.activities.model.User;
import com.projects.el.miniawi.activities.util.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupFragment extends Fragment implements OnClickListener {
    private static View view;
    private static EditText fullName, emailId, mobileNumber, location, password, confirmPassword;
    private static TextView login;
    private static Button signUpButton;
    private static CheckBox terms_conditions;
    private ProgressBar progress;

    public SignupFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.signup_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initialize all views
    private void initViews() {
        progress = (ProgressBar)view.findViewById(R.id.progress);
        fullName = (EditText) view.findViewById(R.id.fullName);
        emailId = (EditText) view.findViewById(R.id.userEmailId);
        mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
        location = (EditText) view.findViewById(R.id.location);
        password = (EditText) view.findViewById(R.id.password);
        confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
        signUpButton = (Button) view.findViewById(R.id.signUpBtn);
        login = (TextView) view.findViewById(R.id.already_user);
        terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);

//        // Setting text selector over textviews
//        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
//        try {
//            ColorStateList csl = ColorStateList.createFromXml(getResources(),
//                    xrp);
//
//            login.setTextColor(csl);
//            terms_conditions.setTextColor(csl);
//        } catch (Exception e) {
//        }
    }

    // Set Listeners
    private void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpBtn:

                // Call checkValidation method

                String name=fullName.getText().toString();
                String phone=emailId.getText().toString();
                String pass=password.getText().toString();
                String confirmPass=confirmPassword.getText().toString();
                if(checkValidation(name,phone,pass,confirmPass)){
                 doRegistration(name,phone,pass,confirmPass);

                };
                break;

            case R.id.already_user:
                // Replace login fragment
                new LoginActivity().replaceLoginFragment();
                break;
        }

    }

    private void doRegistration(String name, String phone, String pass, String confirmPass) {

        progress.setVisibility(View.VISIBLE);
        AuthenticationService authenticationService= ServiceGenerator.createService(AuthenticationService.class);

        User user=new User();
        user.setName(name);
        user.setPhoneNumber(phone);
        user.setPassword(pass);

        ServerRequest serverRequest=new ServerRequest();
        serverRequest.setOperation(Util.REGISTER_OPERATION);
        serverRequest.setUser(user);


       Call<ServerResponse>  call=authenticationService.authenticate(serverRequest);
        call.enqueue(new Callback<ServerResponse>() {

            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
                progress.setVisibility(View.INVISIBLE);
                new LoginActivity().replaceLoginFragment();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                progress.setVisibility(View.INVISIBLE);

            }
        });




    }

    // Check Validation Method
    private boolean checkValidation(String name,String phone,String pass,String confirmPass) {

       boolean valid;


        // Pattern match for email id
        Pattern p = Pattern.compile(Util.regEx_mobile);
        Matcher m = p.matcher(phone);

        // Check if all strings are null or not
        if (name.equals("") || name.length() == 0
                || phone.equals("") || phone.length() == 0
                || phone.equals("") || phone.length() == 0
                || pass.equals("") || pass.length() == 0
                || confirmPass.equals("")
                || confirmPass.length() == 0) {

            new CustomToast().Show_Toast(getActivity(), view,
                    "All fields are required.");
            // Check if email id valid or not
            valid=false;
        }
        else if (!m.find()) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");
            valid=false;

        }

            // Check if both password should be equal
        else if (!confirmPass.equals(pass)) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "Both password doesn't match.");

            valid=false;

        }

            // Make sure user should check Terms and Conditions checkbox
        else if (!terms_conditions.isChecked()) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "Please select Terms and Conditions.");
            valid=false;

        }

            // Else do signup or do your stuff
        else{

            valid=true;
        }

return  valid;
    }
}