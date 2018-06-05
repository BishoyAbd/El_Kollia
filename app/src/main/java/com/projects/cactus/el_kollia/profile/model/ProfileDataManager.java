package com.projects.cactus.el_kollia.profile.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ipaulpro.afilechooser.utils.FileUtils;
import com.projects.cactus.el_kollia.model.User;
import com.projects.cactus.el_kollia.profile.presenter.ProfilePresenterContract;
import com.projects.cactus.el_kollia.util.AppConstants;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by el on 6/13/2017.
 */

public class ProfileDataManager implements ProfileManagerContract {


    private static final String TAG = "ProfileDataManager";
    private Context context;
    private ProfilePresenterContract profilePresenter;

    public ProfileDataManager(Context context, ProfilePresenterContract profilePresenter) {
        this.context = context;
        this.profilePresenter = profilePresenter;
    }

    @Override
    public void getProfileData(String userID) {

//        AuthenticationService authenticationService = ServiceGenerator.createService(AuthenticationService.class);
//        RequestBody requestBody = ServiceGenerator.createFromString(userID);
//        Call<User> call = authenticationService.getUserData(requestBody);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Log.e("ProfileFragment", "on response is called ");
//                profilePresenter.onProfileDataRetrievedSuccessfully(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.e("ProfileFragment", "on error is called ");
//                profilePresenter.onProfileDataRetrievedfailure(t.getLocalizedMessage());
//
//            }
//        });

    }

    @Override
    public void getUserPosts(String userID) {
//        QuestionApiService questionApiService = ServiceGenerator.createService(QuestionApiService.class);
//        QuestionRequest questionRequest = new QuestionRequest(userID);
//        Call<List<Question>> questionCall = questionApiService.loadUsrtQuestions(questionRequest);
//        questionCall.enqueue(new Callback<List<Question>>() {
//            @Override
//            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
//                if (response.body() != null)
//                    profilePresenter.onUserPostsRetrievedSuccessfully(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Question>> call, Throwable t) {
//                profilePresenter.onUserPostsRetrievedfailure(t.getLocalizedMessage());
//            }
//        });
    }


    @Override
    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.PREF_NAME, 0);
        sharedPreferences.edit().putBoolean(AppConstants.KEY_LOGGED_IN, false).apply();

    }


    //upload one or more picture
    @Override
    public void changeUserProfile(String id, String code, Uri path) {

//        UploadingService uploadService = ServiceGenerator.createService(UploadingService.class);
//        MultipartBody.Part fileBody = prepareFilePart("uploaded_file", path);
//        RequestBody idRequestBody = createPartFromString(id);
//        RequestBody codeRequest = createPartFromString(code);
//        Call<Respond> call = uploadService.upload(fileBody, idRequestBody, codeRequest);
//        call.enqueue(new Callback<Respond>() {
//            @Override
//            public void onResponse(Call<Respond> call, Response<Respond> response) {
//
//                Log.d(TAG, "message is ----> " + response.body().getMessage() + ".....with error ---->" + response.body().getError());
//                if (!response.body().getError())
//                    profilePresenter.onProfileDataChangedSuccessfully();
//                else
//                    profilePresenter.onProfilePhotoChangedFailure(response.body().getMessage());
//            }
//
//
//            @Override
//            public void onFailure(Call<Respond> call, Throwable t) {
//                Log.d(TAG, "on Failure is called " + t.getLocalizedMessage());
//                profilePresenter.onProfilePhotoChangedFailure(t.getLocalizedMessage());
//            }
//        });


    }


    @Override
    public void changeUserData(User user) {

    }

    @NonNull
    private RequestBody createPartFromString(String token) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, token);
    }


    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(context, fileUri);
        Log.d(TAG, "photo name : " + file.getAbsolutePath());

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(context.getContentResolver().getType(fileUri)),
                        file);
        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }


}
