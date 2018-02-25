package com.projects.cactus.el_kollia.profile.presenter;


import android.content.Context;
import android.net.Uri;

import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.User;
import com.projects.cactus.el_kollia.profile.model.ProfileDataManager;
import com.projects.cactus.el_kollia.profile.view.ProfileView;

import java.util.List;


/**
 * Created by el on 6/13/2017.
 */

public class ProfilePresenter implements ProfilePresenterContract {

    ProfileDataManager profileDataManager;
    private ProfileView profileView;
    Context context;

    public ProfilePresenter(Context context, ProfileView profileView) {
        this.profileView = profileView;
        this.context = context;
        profileDataManager = new ProfileDataManager(context, this);
    }


    @Override
    public void getProfileData(String uerId) {
        profileDataManager.getProfileData(uerId);
    }

    @Override
    public void onProfileDataRetrievedSuccessfully(User user) {
        profileView.onProfileDataRetrievedSuccessfully(user);

    }

    @Override
    public void onProfileDataRetrievedfailure(String error) {
        profileView.onProfileDataRetrievedfailure(error);
    }

    @Override
    public void onUserPostsRetrievedSuccessfully(List<Question> questions) {
        this.profileView.onUserPostsRetrievedSuccessfully(questions);
    }

    @Override
    public void onUserPostsRetrievedfailure(String error) {
        profileView.onUserPostsRetrievedfailure(error);
    }

    @Override
    public void getUserPosts(String userId) {
        profileDataManager.getUserPosts(userId);
    }

    @Override
    public void changeUserPhoto(String id, String code, Uri path) {
        profileDataManager.changeUserProfile( id,  code,  path);
    }

    @Override
    public void logout() {
        profileDataManager.logout();
    }

    @Override
    public void onProfileDataChangedSuccessfully() {

    }

    @Override
    public void onProfilePhotoChangedFailure(String error) {
profileView.onProfilePhotoChangedFailure(error);
    }

    @Override
    public void onProfilePhotoChangedSuccessfully() {
        profileView.onProfilePhotoChangedSuccessfully();
    }
}
