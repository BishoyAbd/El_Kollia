package com.projects.cactus.el_kollia.Activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.projects.cactus.el_kollia.ApiServices.AuthenticationService;
import com.projects.cactus.el_kollia.ApiServices.ServiceGenerator;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.adapters.MyCustomSpinnerAdapter;
import com.projects.cactus.el_kollia.customviews.CustomToast;
import com.projects.cactus.el_kollia.model.ServerRequest;
import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.User;
import com.projects.cactus.el_kollia.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupFragment extends Fragment implements OnClickListener {
    private static View view;
    private static EditText fullName, emailId, mobileNumber, location, password, confirmPassword;
    private static TextView alreadyUser;
    private static Button signUpButton;
    private static CheckBox terms_conditions;
    private ProgressBar progress;
    String[] yearsLis = {"preparatory", "first", "second", "third", "fourth"};
    String[] depList = {"general", "control", "CS", "communication"};

    List arrayListYears = new ArrayList(Arrays.asList(yearsLis));
    List arrayListDeparts = new ArrayList(Arrays.asList(depList));

    Spinner yearsSpinner, departmentsSpinner;

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
        progress = (ProgressBar) view.findViewById(R.id.progress);
        fullName = (EditText) view.findViewById(R.id.fullName);
        emailId = (EditText) view.findViewById(R.id.userEmailId);
        password = (EditText) view.findViewById(R.id.password);
        confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
        signUpButton = (Button) view.findViewById(R.id.signUpBtn);
        alreadyUser = (TextView) view.findViewById(R.id.already_user);

        yearsSpinner = (Spinner) view.findViewById(R.id.year_sp_id);
        ArrayAdapter mySpinnerAdapter = new MyCustomSpinnerAdapter(getActivity(), R.layout.custom_drop_down_spinner, arrayListYears);
        yearsSpinner.setAdapter(mySpinnerAdapter);


        departmentsSpinner = (Spinner) view.findViewById(R.id.department_sp_id);
        ArrayAdapter depaArrayAdapter = new MyCustomSpinnerAdapter(getActivity(), R.layout.custom_drop_down_spinner, arrayListDeparts);
        departmentsSpinner.setAdapter(depaArrayAdapter);

//testing

        fullName.setText("bishoy");
        emailId.setText("abram@el-eng.menoufia.edu.eg");
        password.setText("qw");
        confirmPassword.setText("qw");


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
        alreadyUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpBtn:

                // Call checkValidation method

                String name = fullName.getText().toString();
                String email = emailId.getText().toString();
                String pass = password.getText().toString();


                String confirmPass = confirmPassword.getText().toString();
                if (checkValidation(name, email, pass, confirmPass)) {
                    doRegistration(name, email, pass, confirmPass);

                }
                ;
                break;

            case R.id.already_user:
                // Replace login fragment
                getActivity().getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                        .replace(R.id.frag_container, new LoginFragment(), Util.Login_Fragment).commit()
                ;
                break;
        }

    }

    private void doRegistration(String name, String email, String pass, String confirmPass) {

        progress.setVisibility(View.VISIBLE);
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
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
                progress.setVisibility(View.INVISIBLE);
                ((WelcomeActivity) getActivity()).replaceLoginFragment();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                progress.setVisibility(View.INVISIBLE);

            }
        });


    }

    // Check Validation Method
    private boolean checkValidation(String name, String email, String pass, String confirmPass) {

        boolean valid;


        // Pattern match for email id
        Pattern p = Pattern.compile(Util.regEx_email);
        Matcher m = p.matcher(email);

        // Check if all strings are null or not
        if (name.equals("") || name.length() == 0
                || email.equals("") || email.length() == 0
                || email.equals("") || email.length() == 0
                || pass.equals("") || pass.length() == 0
                || confirmPass.equals("")
                || confirmPass.length() == 0) {

            new CustomToast().Show_Toast(getActivity(), view,
                    "All fields are required.");
            // Check if email id valid or not
            valid = false;
        } else if (!m.find()) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");
            valid = false;

        }

        // Check if both password should be equal
        else if (!confirmPass.equals(pass)) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "Both password doesn't match.");

            valid = false;

        }

        // Make sure user should check Terms and Conditions checkbox

        // Else do signup or do your stuff
        else {

            valid = true;
        }

        return valid;
    }


}