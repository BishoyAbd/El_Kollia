package com.projects.cactus.el_kollia.feed.view;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projects.cactus.el_kollia.Activity.PostActivity;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.feed.adapter.ButtonVoteListener;
import com.projects.cactus.el_kollia.feed.adapter.PostsAdapter;
import com.projects.cactus.el_kollia.feed.adapter.QuestionsRecyclerAdapter;
import com.projects.cactus.el_kollia.feed.presenter.FeedPresenter;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.util.Util;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.LinearSort;
import com.willowtreeapps.spruce.sort.RadialSort;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by el on 4/14/2017.
 */

public class FeedFragment extends Fragment implements QuestionDialog.OnDialogButtonClick, FeedView, ButtonVoteListener {

    FloatingActionButton addQuestion;
    SwipeRefreshLayout swipeRefreshLayout;
    private static final String TAG = "FeedFragment";
    @NonNull
    PostsAdapter questionsRecyclerAdapter;
    RecyclerView recyclerView;
    ViewGroup parent;
    private String userId;
    private Context activity;

    int viewHolderPosition = -1;
    private List<Question> questions = new ArrayList<>();
    private FeedPresenter feedPresenter;
    private java.lang.Object attachingActivityLock = new Object();
    private Animator spruceAnimator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.feed_fragment, viewGroup, false);
        parent = viewGroup;
        initializViews(view);
        setListener();
        QuestionDialog questionDialog = new QuestionDialog();
        questionDialog.setTargetFragment(this, 0);
        prepareQuestions(view);
        feedPresenter.getPosts();
        Log.d(TAG, "onCreateView Iscalled");

        return view;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedPresenter = new FeedPresenter(this);
    }

    void initializViews(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swip_refresh_get_questions);
        addQuestion = (FloatingActionButton) view.findViewById(R.id.add_quest_fab_id);

    }

    void setListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                feedPresenter.refresh();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionDialog questionDialog = new QuestionDialog();
                questionDialog.setTargetFragment(FeedFragment.this, 0);
                new QuestionDialog().show(getActivity().getSupportFragmentManager(), "QuestionDialog");
            }
        });

    }


    void prepareQuestions(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.questions_recyclerView_id);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                super.onLayoutChildren(recycler, state);
//                // Animate in the visible children
//                spruceAnimator = new Spruce.SpruceBuilder(recyclerView)
//                        .sortWith(new LinearSort(80l,true, LinearSort.Direction.LEFT_TO_RIGHT))
//                        .animateWith(DefaultAnimations.spinAnimator(recyclerView, 800))
//                        .start();

            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(questionsRecyclerAdapter);


    }

    private void openPostActivity(int qId) {

        Intent intent = new Intent(getActivity(), PostActivity.class);
        intent.putExtra(Util.QUESTION_ID_EXRA, qId);
        startActivity(intent);
    }


    private void animate(ViewGroup viewGroup) {

        Animator spruceAnimator = new Spruce
                .SpruceBuilder(viewGroup)
                .sortWith(new RadialSort(2000L, true, RadialSort.Position.TOP_LEFT))
                .animateWith(new Animator[]{DefaultAnimations.shrinkAnimator(viewGroup, 800L)})
                .start();
    }

//

    @Override
    public void onClickPost(String content) {
        Log.d(TAG, "uploading");
        if (userId == null || userId.equals(Util.ERROR_ID_INVALIDE)) {
            userId = MainActivity.unique_id;
            Log.d(TAG, "user_id from pref ---> " + userId);
            if (!userId.equals(Util.ERROR_ID_INVALIDE) || userId != null)
                post(userId, content);
            else {
                //show error and open login activity after clearing keep me loged in
            }
        }

    }

    @Override
    public void onClickCancel() {

    }



    @Override
    public void getAllPosts() {

    }

    @Override
    public void post(String userId, String post) {
        if (feedPresenter == null) {
//            process();
            if (this.activity == null)
                Log.d(TAG, "getActivity returned null from feed fragment");
            feedPresenter = new FeedPresenter(this);

        }
        feedPresenter.post(userId, post);
    }

    @Override
    public void onPostRetrievedSuccess(List<Question> posts) {
        questions = posts;

        questionsRecyclerAdapter = new PostsAdapter(getActivity(), questions, MainActivity.unique_id, this);
        recyclerView.setAdapter(questionsRecyclerAdapter);


    }

    @Override
    public void onPostRetrievdFailure(String error) {
        Snackbar.make(parent, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void tryUpVote(int questionId, String userId) {
        Log.d(TAG,"user id : "+userId+"-------> question id : "+questionId );
        feedPresenter.upvote(questionId, userId);

    }

    @Override
    public void upvotedSuccess() {
        //make the button green
        if (viewHolderPosition != -1)
            updateUpVoteBtn(viewHolderPosition);

    }

    private void updateUpVoteBtn(int viewHolderPosition) {

        PostsAdapter.QViewHolder vh = (PostsAdapter.QViewHolder) recyclerView.findViewHolderForAdapterPosition(viewHolderPosition);

        View v = vh.upVote_btn;
        // if it is not pressed
        if (!vh.itemView.isPressed()) {
            v.setBackground(getResources().getDrawable(R.drawable.likegreen_btn));
            vh.itemView.setPressed(true);

            Log.d(TAG, " likegreen is set pressed -> " + vh.itemView.isPressed());
            int votes = Integer.parseInt(((Button) v).getText().toString());
            ((Button) v).setText((votes + 1) + "");
        } else {
            v.setBackground(getResources().getDrawable(R.drawable.like_btn));
            vh.itemView.setPressed(false);
            Log.d(TAG, " like  is set pressed -> " + vh.itemView.isPressed());
            int votes = Integer.parseInt(((Button) v).getText().toString());
            ((Button) v).setText((votes - 1) + "");

        }
    }


    @Override
    public void upvotedFailure(String error) {
        //show upvote error

    }

    //-------------------------called when button upvote is clicked ----delegated from the recyclerAdapter--QViewHolder
    @Override
    public void ButtonUpVoteOnClick(View v, int position) {
        viewHolderPosition = position;
        //buttonUpvote = (Button) v;
        Question question = questions.get(position);
        tryUpVote(question.getId(),question.getUser_id());
    }


    @Override
    public void onStop() {
        super.onStop();
        if (questionsRecyclerAdapter!=null)
        questionsRecyclerAdapter.clear();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.d(TAG, "onAttach Iscalled ...activity --> " + activity);

    }




    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void ButtonDownVoteViewOnClick(View v, int position) {
//to be implemented later later
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPauseIscalled");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResumeIscalled");
        if (spruceAnimator!=null)
            spruceAnimator.start();


    }


}

