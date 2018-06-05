package com.projects.cactus.el_kollia.feed;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.nex3z.flowlayout.FlowLayout;
import com.projects.cactus.el_kollia.Activity.MainActivity;
import com.projects.cactus.el_kollia.Activity.QuestionActivity;
import com.projects.cactus.el_kollia.ApiServices.QuestionApiService;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.application.ElKollyaApp;
import com.projects.cactus.el_kollia.data.LocalAuthStore;
import com.projects.cactus.el_kollia.feed.adapter.FeedAdapter;
import com.projects.cactus.el_kollia.feed.adapter.FeedAdapterContract;
import com.projects.cactus.el_kollia.feed.di.DaggerFeedComponenet;
import com.projects.cactus.el_kollia.feed.di.FeedComponenet;
import com.projects.cactus.el_kollia.feed.di.FeedModule;
import com.projects.cactus.el_kollia.like.LikeInteractorContract;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.QuestionRequest;
import com.projects.cactus.el_kollia.model.TAG;
import com.projects.cactus.el_kollia.util.AppConstants;
import com.projects.cactus.el_kollia.util.Helper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;


/**
 * Created by el on 4/14/2017.
 */

public class FeedFragment extends Fragment implements QuestionDialog.OnDialogButtonClick, FeedContract.View, MainActivity.FragmentStringCommunicator {


    @BindView(R.id.add_quest_fab_id)
    FloatingActionButton addQuestion;
    @BindView(R.id.swip_refresh_get_questions)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.questions_recyclerView_id)
    RecyclerView recyclerView;
    @BindView(R.id.tags_flowLayout)
    FlowLayout flowLayout;

    @BindView(R.id.error_view_root)
    RelativeLayout errorView;


    @BindView(R.id.retry_btn)
    Button retryBtn;

    ProgressDialog progressDialog;

    QuestionApiService questionApiService;
    @Inject
    LocalAuthStore localAuthStore;
    @Inject
    FeedInteractor feedInteractor;
    @Inject
    FeedContract.Presenter feedPresenter;
    @Inject
    LikeInteractorContract liekInteractor;
    @Inject
    FeedAdapterContract.Presenter adapterPresenter;

    FeedAdapter feedAdapter;
    private Unbinder unbinder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.feed_fragment, viewGroup, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        feedPresenter.subscribe(this);
        feedPresenter.getPosts(new QuestionRequest());
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FeedComponenet daggerApplicationComponent = DaggerFeedComponenet.builder()
                .feedModule(new FeedModule((MainActivity) getActivity()))
                .applicationComponent(((ElKollyaApp) getActivity().getApplication()).getApplicationComponent())
                .build();

        daggerApplicationComponent.inject((MainActivity) getActivity());
    }

    private void init() {
        progressDialog = new ProgressDialog(getActivity());
        setupRefresh();
        prepareQuestions();
        testTags();
        test();

    }

    //test
    @Override
    public void setupTags(List<TAG> tags) {


        String[] colors = new String[]{"#FBFAE1", "#CEF0B9", "#64A36F", "#FFE121", "#FDC57B", "#F73859", "#DBEDF3"};
        int i = 0;
        for (TAG tag : tags) {
            Button button = new Button(getActivity());
            button.setText(tag.getTag());
            button.setBackgroundResource(R.drawable.tag_btn_selector);
            GradientDrawable drawable = (GradientDrawable) button.getBackground();
            drawable.setColor(Color.parseColor(colors[i]));
            button.setOnClickListener(view -> {
                cleanStrokeAndAlpha(flowLayout);
                //  drawable.setStroke(4, Color.BLUE);
                button.setAlpha(.6f);
                feedPresenter.filter(((Button) view).getText().toString());

            });
            flowLayout.addView(button);

            //set width
            FlowLayout.LayoutParams params = button.getLayoutParams();
            params.width = FlowLayout.LayoutParams.WRAP_CONTENT;
            params.height = Helper.dpToPx(getActivity(), 30);
            button.setLayoutParams(params);

            i++;
        }


    }

    private void cleanStrokeAndAlpha(ViewGroup flowLayout) {
        for (int i = 0; i < flowLayout.getChildCount(); i++) {
            // ((GradientDrawable) flowLayout.getChildAt(i).getBackground()).setAlpha(100);
            flowLayout.getChildAt(i).setAlpha(1);
        }
    }

    //test
    void testTags() {
        //tags
        List<TAG> tags = new ArrayList<>();
        TAG tag = new TAG("java");
        tags.add(tag);
        tag = new TAG("os");
        tags.add(tag);
        tag = new TAG("embedded");
        tags.add(tag);
        tag = new TAG("SWE");
        tags.add(tag);
        tag = new TAG("All");
        tags.add(tag);
        tag = new TAG("C++");
        tags.add(tag);
        tag = new TAG("Big data");
        tags.add(tag);

        setupTags(tags);


    }

    // test
    private void test() {

//        localAuthStore = new LocalAuthStore(getActivity());
//        feedInteractor = new FeedInteractorImp(questionApiService, localAuthStore);
//        feedPresenter = new FeedPresenter(feedInteractor, schedulerProvider);
//        questionApiService = ServiceGenerator.createService(QuestionApiService.class);
//        liekInteractor = new LikeInteractor(questionApiService);
//        adapterPresenter = new FeedAdapterPresenter(liekInteractor);
    }

    private void setupRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            feedPresenter.getPosts(new QuestionRequest());
            swipeRefreshLayout.setRefreshing(false);

        });

        addQuestion.setOnClickListener(view -> {
            QuestionDialog questionDialog = new QuestionDialog();
            questionDialog.setTargetFragment(FeedFragment.this, 0);
            new QuestionDialog().show(getActivity().getSupportFragmentManager(), "QuestionDialog");
        });

    }


    private void prepareQuestions() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void openPostActivity(int questionId) {
        Intent intent = new Intent(getActivity(), QuestionActivity.class);
        intent.putExtra(AppConstants.QUESTION_ID_EXRA, questionId);
        startActivity(intent);
    }

    @Override
    public void showFilteredData(String string) {
        feedAdapter.filter(string);
    }

    /*
    TODO :: change this method only presenter can see the ID

    both methods belongs to question dialog
     */
    @Deprecated
    @Override
    public void onClickPost(String content) {
//        Log.d(TAG, "uploading");
//        if (userId == null || userId.equals(AppConstants.ERROR_ID_INVALIDE)) {
//            userId = MainActivity.unique_id;
//            Log.d(TAG, "user_id from pref ---> " + userId);
//            if (!userId.equals(AppConstants.ERROR_ID_INVALIDE) || userId != null)
//                post(userId, content);
//            else {
//                //show error and open login activity after clearing keep me loged in
//            }
//        }

    }


    @Override
    public void onClickCancel() {

    }


    @Override
    public void showPosts(List<Question> posts) {
        adapterPresenter.setQuestions(posts);
        feedAdapter = new FeedAdapter(getActivity(), adapterPresenter);
        recyclerView.setAdapter(feedAdapter);

    }


    @Override
    public void setPresenter(FeedContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog.isShowing())
            progressDialog.hide();
    }

    @Override
    public void showError() {
        errorView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideError() {
        errorView.setVisibility(View.INVISIBLE);
    }

    @Override
    @OnClick(R.id.retry_btn)
    public void onClickRetry() {
        feedPresenter.getPosts(new QuestionRequest());
    }

    @Override
    public void onPause() {
        super.onPause();
        feedPresenter.unsubscribe();
        Timber.d("onPauseIscalled");
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.d("onResumeIscalled");
        feedPresenter.subscribe(this);
        feedPresenter.getPosts(new QuestionRequest());
    }

    @Override
    public void onStop() {
        super.onStop();
        feedAdapter.clear();
        feedPresenter.unsubscribe();
    }

    /**
     * @param stringInQuestion receives a call from HomeFragment which receives a call from MainActivity when search is changed or submitted
     */
    @Override
    public void notifyFragment(String stringInQuestion) {
        feedPresenter.find(stringInQuestion);
    }
}

