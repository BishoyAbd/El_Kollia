package com.projects.cactus.el_kollia.authentication.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.projects.cactus.el_kollia.BuildConfig;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.adapters.MyCustomSpinnerAdapter;
import com.projects.cactus.el_kollia.authentication.presenter.SignUpPresenter;
import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.User;
import com.projects.cactus.el_kollia.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignupFragment extends Fragment implements OnClickListener, SignUpView {

    private  View view;
    private  EditText fullName, emailId, mobileNumber, location, password, confirmPassword;
    private  TextView alreadyUser;
    private  Button signUpButton;
    private  CheckBox terms_conditions;
    private ProgressDialog progress;
    private String[] yearsLis = {"preparatory", "first", "second", "third", "fourth"};
    private String[] depList = {"general", "control", "CS", "communication"};

    private List arrayListYears = new ArrayList(Arrays.asList(yearsLis));
    private List arrayListDeparts = new ArrayList(Arrays.asList(depList));
    private Spinner yearsSpinner, departmentsSpinner;

    private SignUpPresenter presenter;

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

        if (BuildConfig.DEBUG) {
            fullName.setText("bishoy");
            emailId.setText("abram@el-eng.menoufia.edu.eg");
            password.setText("qw");
            confirmPassword.setText("qw");
        }
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

                showLoading();
                String name = fullName.getText().toString();
                String email = emailId.getText().toString();
                String pass = password.getText().toString();
                String confirmPass = confirmPassword.getText().toString();
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(pass);
                user.setConfirmPassword(confirmPass);

                presenter = new SignUpPresenter(this);
                presenter.signUp(user);

                break;

            case R.id.already_user:
                // Replace login fragment
                replaceLoginFragment();
                break;
        }

    }


    void replaceLoginFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frag_container, new LoginFragment(), Util.Login_Fragment).commit()
        ;
    }


    @Override
    public void onSignUpSuccess(ServerResponse serverResponse) {
        if (progress!=null)
        progress.dismiss();
        replaceLoginFragment();
    }

    @Override
    public void onSignUpError(String error) {
        progress.setMessage(error);
        progress.show();
//make toast
    }

    @Override
    public void showLoading() {
        progress=new ProgressDialog(getActivity());
        progress.setMessage("Loading...");
        progress.show();

    }

    @Override
    public void hideLoading() {
        if (!progress.isShowing())
        progress.hide();

    }
}