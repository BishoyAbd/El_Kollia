package com.projects.cactus.el_kollia.profile.model;

import android.net.Uri;

import com.projects.cactus.el_kollia.model.User;

import java.io.File;
import java.util.List;

/**
 * Created by el on 6/13/2017.
 */

public interface ProfileManagerContract

{
    void getProfileData(String userID);
    void getUserPosts(String userID);
    void logout();
    void changeUserProfile(String id,String code ,Uri path);
    void changeUserData(User user);

}
