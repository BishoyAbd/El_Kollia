package com.projects.cactus.el_kollia.profile.presenter;

import android.net.Uri;

import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.User;

import java.util.List;

/**
 * Created by el on 6/13/2017.
 */

public interface ProfilePresenterContract {

    void getProfileData(String uerId);
    void onProfileDataRetrievedSuccessfully(User user);
    void onProfileDataRetrievedfailure(String error);
    void onUserPostsRetrievedSuccessfully(List<Question> questions);
    void onUserPostsRetrievedfailure(String error);
    void getUserPosts(String userId);
    void changeUserPhoto(String id,String code,Uri path);
    void logout();
    void onProfileDataChangedSuccessfully();
    void onProfilePhotoChangedFailure(String error);
    void onProfilePhotoChangedSuccessfully();



}
