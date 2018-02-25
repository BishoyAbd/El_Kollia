package com.projects.cactus.el_kollia.profile.view;

import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.User;

import java.io.File;
import java.util.List;

/**
 * Created by el on 6/13/2017.
 */

public interface ProfileView {

    void getProfileData(String uerId);
    void onProfileDataRetrievedSuccessfully(User user);
    void onProfileDataRetrievedfailure(String error);
    void onUserPostsRetrievedSuccessfully(List<Question> questions);
    void onUserPostsRetrievedfailure(String error);
    void logout();
    void showLoading();
    void hideLoading();
    void changeUserData(User user);
    void changeProfilePicture(String userID,File Path);
    void onProfileDataChangedSuccessfully();
    void onProfilePhotoChangedFailure(String error);
    void onProfilePhotoChangedSuccessfully();
}
