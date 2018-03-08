package com.projects.cactus.el_kollia.profile.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.feed.adapter.PostsAdapter;
import com.projects.cactus.el_kollia.feed.FeedPresenter;
import com.projects.cactus.el_kollia.feed.FeedView;
import com.projects.cactus.el_kollia.feed.MainActivity;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.User;
import com.projects.cactus.el_kollia.profile.presenter.ProfilePresenter;
import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.define.Define;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by el on 4/15/2017.
 */

public class ProfileFragment extends Fragment implements ProfileView, FeedView {


    private static final String TAG = "ProfileFragment";
    String email;
    String password;
    private User user;

    CircleImageView profilePhoto;
    TextView reputationTv, numPostsTv, nameTv, departmentTv, YearTv;
    FloatingActionButton logoutBtn;
    ProfilePresenter profilePresenter;
    private TextView bioTv;
    private View view;
    private ImageView coverPhoto;
    private RecyclerView userPostRecyclerView;
    private RecyclerView.Adapter questionsRecyclerAdapter;
    private ProgressDialog progressDialog;
    private FeedPresenter feedPresenter;
    private ArrayList<Uri> path;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)

    {
        View v = inflater.inflate(R.layout.profile_3, container, false);
        view = v;
        initializeView(v);
        profilePresenter = new ProfilePresenter(getActivity(), this);
        feedPresenter = new FeedPresenter(this);
        getProfileData(MainActivity.getUnique_id());
        // getUserPosts(MainActivity.getUnique_id());


        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGallery();
            }
        });

        v.findViewById(R.id.logOutBtn);
        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    void prepareQuestions(View view) {

        userPostRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_posts_in_profile);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        userPostRecyclerView.setLayoutManager(layoutManager);


    }


    void initializeView(View view) {
        logoutBtn = (FloatingActionButton) view.findViewById(R.id.logOutBtn);
        profilePhoto = (CircleImageView) view.findViewById(R.id.profile_photo);
        coverPhoto = (ImageView) view.findViewById(R.id.cover_photo_diagonal);
        reputationTv = (TextView) view.findViewById(R.id.reputation_tv_profile);
        nameTv = (TextView) view.findViewById(R.id.user_name_tv_profile);
        numPostsTv = (TextView) view.findViewById(R.id.tv_number_of_posts_profile);
        bioTv = (TextView) view.findViewById(R.id.bio_tv_profile);
        YearTv = (TextView) view.findViewById(R.id.year_tv_profile);
        departmentTv = (TextView) view.findViewById(R.id.department_tv_profile);

        prepareQuestions(view);

    }


    void getUserPosts(String userId) {
        //don't forget back stack


    }


    @Override
    public void getProfileData(String userId) {
        profilePresenter.getProfileData(userId);

    }

    @Override
    public void onProfileDataRetrievedSuccessfully(User user) {
        Glide.with(getActivity()).load(R.drawable.profile).into(profilePhoto);
        // Glide.with(getActivity()).load(user.getProfile_url()).into(coverPhoto);
        nameTv.setText(user.getName());
        bioTv.setText(user.getBio());
        departmentTv.setText(user.getDepartment());
        YearTv.setText(user.getAcademic_year());


    }

    @Override
    public void onProfileDataRetrievedfailure(String error) {
        Snackbar.make(view, error, Snackbar.LENGTH_SHORT).show();


    }

    @Override
    public void onUserPostsRetrievedSuccessfully(List<Question> questions) {

        questionsRecyclerAdapter = new PostsAdapter(getActivity(), questions, MainActivity.getUnique_id(), null);
        userPostRecyclerView.setAdapter(questionsRecyclerAdapter);
    }

    @Override
    public void onUserPostsRetrievedfailure(String error) {
        Snackbar.make(view, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void logout() {
        profilePresenter.logout();
    }

    @Override
    public void showLoading() {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading");
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog.isShowing())
            progressDialog.hide();
    }

    @Override
    public void getAllPosts() {

    }

    @Override
    public void post(String userId, String post) {

    }

    @Override
    public void showPosts(List<Question> posts) {
        onUserPostsRetrievedSuccessfully(posts);
    }

    @Override
    public void onPostRetrievdFailure(String error) {

    }

    @Override
    public void tryUpVote(int questionId, String userId) {

    }

    @Override
    public void upvotedSuccess() {

    }

    @Override
    public void upvotedFailure(String error) {

    }

    @Override
    public void changeUserData(User user) {

    }

    @Override
    public void changeProfilePicture(String userID, File Path) {

    }

    @Override
    public void onProfileDataChangedSuccessfully() {

    }


    @Override
    public void onProfilePhotoChangedFailure(String error) {

    }

    @Override
    public void onProfilePhotoChangedSuccessfully() {
        // Glide.with(getActivity()).load(FileUtils.getFile(getActivity(),path.get(0))).into(profilePhoto);
        profilePhoto.setImageURI(path.get(0));
    }


    void startGallery() {
        FishBun.with(this)
                .setPickerCount(3)
                .setPickerSpanCount(3)
                .setActionBarColor(Color.parseColor("#3F51B5"), Color.parseColor("#303F9F"))
                .setActionBarTitleColor(Color.parseColor("#000000"))
                .textOnImagesSelectionLimitReached("Limit Reached!")
                .textOnNothingSelected("Nothing Selected")
                .setAlbumSpanCount(2, 4)
                .setArrayPaths(path)
                .setButtonInAlbumActivity(true)
                .setCamera(true)
                .setReachLimitAutomaticClose(false)
                .setAllViewTitle("All")
                .setActionBarTitle("Image Library")
                .startAlbum();


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent imageData) {
        Log.d(TAG, "on acttivity result is called");
        switch (requestCode) {
            case Define.ALBUM_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    path = imageData.getParcelableArrayListExtra(Define.INTENT_PATH);
                    profilePresenter.changeUserPhoto(MainActivity.getUnique_id(), "profile", path.get(0));
                    break;
                }
        }

    }

}
