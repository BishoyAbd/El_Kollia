package com.projects.cactus.el_kollia.authentication.presenter;

import com.projects.cactus.el_kollia.customviews.CustomToast;
import com.projects.cactus.el_kollia.util.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by el on 6/7/2017.
 */

public class AuthenticationInteractor {


    public static final String ERROR_INVALID_CRED="invalid credentials!";

    public boolean checkValidation(String name, String email, String pass, String confirmPass) {

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

            //new CustomToast().Show_Toast(getActivity(), view,
            //   "All fields are required.");
            // Check if email id valid or not
            valid = false;
        } else if (!m.find()) {
            // new CustomToast().Show_Toast(getActivity(), view,
            //      "Your Email Id is Invalid.");
            valid = false;

        }

        // Check if both password should be equal
        else if (!confirmPass.equals(pass)) {
            //   new CustomToast().Show_Toast(getActivity(), view,
            //  "Both password doesn't match.");

            valid = false;

        }

        // Make sure user should check Terms and Conditions checkbox

        // Else do signup or do your stuff
        else {

            valid = true;
        }

        return valid;
    }


    // Check Validation before login
    public boolean checkValidation(String email, String pass) {
        boolean valid;
        // Get email id and password

        // Check patter for email id
        Pattern p = Pattern.compile(Util.regEx_email);

        Matcher m = p.matcher(email);

        // Check for both field is empty or not
        if (email.equals("") || email.length() == 0
                || pass.equals("") || pass.length() == 0) {
//            loginLayout.startAnimation(shakeAnimation);
//            new CustomToast().Show_Toast(getActivity(), view,
//                    "Enter both credentials.");
            valid=false;

        }
        // Check if email id is valid or not
        else if (!m.find()) {
//            new CustomToast().Show_Toast(getActivity(), view,
//                    "Your Email Id is Invalid.");
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
