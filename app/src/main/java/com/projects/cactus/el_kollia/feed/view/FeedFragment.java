package com.projects.cactus.el_kollia.feed.view;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.projects.cactus.el_kollia.feed.adapter.QuestionsRecyclerAdapter;
import com.projects.cactus.el_kollia.feed.presenter.FeedPresenter;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.state.QuestionStateWrapper;
import com.projects.cactus.el_kollia.state.State;
import com.projects.cactus.el_kollia.util.Util;
import com.twotoasters.jazzylistview.effects.CardsEffect;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.LinearSort;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by el on 4/14/2017.
 */

public class FeedFragment extends Fragment implements QuestionDialog.OnDialogButtonClick, FeedView,ButtonVoteListener {

    FloatingActionButton addQuestion;
    SwipeRefreshLayout swipeRefreshLayout;
    private static final String TAG = "FeedFragment";
    QuestionsRecyclerAdapter questionsRecyclerAdapter;
    RecyclerView recyclerView;
    ViewGroup parent;
    Button buttonUpvote;

    int viewHolderPosition=-1;
    private List<Question> questions = new ArrayList<>();
    private FeedPresenter feedPresenter;

//    State upVoteState;
//    QuestionStateWrapper questionStateWrapper = new QuestionStateWrapper();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.feed_fragment, viewGroup, false);
        parent = viewGroup;
        initializViews(view);
        setListener();
        QuestionDialog questionDialog = new QuestionDialog();
        questionDialog.setTargetFragment(this, 0);

        prepareQuestions(view);
        feedPresenter = new FeedPresenter(this);
        feedPresenter.getPosts();
        return view;

    }


    void initializViews(View view){
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swip_refresh_get_questions);
        addQuestion = (FloatingActionButton) view.findViewById(R.id.add_quest_fab_id);

    }

    void setListener(){
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
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(questionsRecyclerAdapter);
        JazzyRecyclerViewScrollListener jazzyRecyclerViewScrollListener = new JazzyRecyclerViewScrollListener();
        jazzyRecyclerViewScrollListener.setTransitionEffect(new CardsEffect());
        recyclerView.setOnScrollListener(jazzyRecyclerViewScrollListener);


    }

    private void openPostActivity(int qId) {

        Intent intent = new Intent(getActivity(), PostActivity.class);
        intent.putExtra(Util.QUESTION_ID_EXRA, qId);
        startActivity(intent);
    }


    private void animate(ViewGroup viewGroup) {

        Animator spruceAnimator = new Spruce
                .SpruceBuilder(viewGroup)
                .sortWith(new LinearSort(500L, false, LinearSort.Direction.TOP_TO_BOTTOM))
                .animateWith(new Animator[]{DefaultAnimations.shrinkAnimator(viewGroup, 800L)})
                .start();
    }

//

    @Override
    public void onClickPost(String content) {
        Log.d(TAG, "uploading");
        feedPresenter.post("id", content);
    }

    @Override
    public void onClickCancel() {

    }


    //when dislike is pressed
    void changeButtonToDislike() {


    }

    //when like pressed
    void changeButtonToLike() {

    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        questionsRecyclerAdapter.clear();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getAllPosts() {

    }

    @Override
    public void post(String userId, String post) {

    }

    @Override
    public void onPostRetrievedSuccess(List<Question> posts) {
          questions=posts;
    questionsRecyclerAdapter=new QuestionsRecyclerAdapter(getActivity(),questions,MainActivity.unique_id,this);
        recyclerView.setAdapter(questionsRecyclerAdapter);


    }

    @Override
    public void onPostRetrievdFailure(String error) {

    }

    @Override
    public void tryUpVote(String userId,String questionId) {
           feedPresenter.upvote(userId, questionId);
    }

    @Override
    public void upvotedSuccess() {
        //make the button green
        if (viewHolderPosition!=-1)
            updateUpVoteBtn(viewHolderPosition);

    }

    private void updateUpVoteBtn( int viewHolderPosition) {

        QuestionsRecyclerAdapter.QViewHolder vh= (QuestionsRecyclerAdapter.QViewHolder) recyclerView.findViewHolderForAdapterPosition(viewHolderPosition);

              View v=vh.upVote_btn;
        // if it is not pressed
        if ( !vh.itemView.isPressed()){
            v.setBackground(getResources().getDrawable(R.drawable.likegreen_btn));
            vh.itemView.setPressed(true);

            Log.d(TAG," likegreen is set pressed -> "+ vh.itemView.isPressed() );
            int  votes= Integer.parseInt(((Button) v).getText().toString());

            ( (Button)v).setText((votes+1)+"");
        }
        else {
            v.setBackground(getResources().getDrawable(R.drawable.like_btn));
            vh.itemView.setPressed(false);
            Log.d(TAG, " like  is set pressed -> " + vh.itemView.isPressed());
            int  votes= Integer.parseInt(((Button) v).getText().toString());

            ( (Button)v).setText((votes-1)+"");

        }
    }


    @Override
    public void upvotedFailure(String error) {
        //show upvote error
    }

    //-------------------------called when button upvote is clicked ----delegated from the recyclerAdapter--QViewHolder
    @Override
    public void ButtonUpVoteOnClick(View v, int position) {
        viewHolderPosition=position;
        //buttonUpvote = (Button) v;

        Question question=questions.get(position);
        tryUpVote(question.getUnique_id(),question.getQuestion_id());
    }

    @Override
    public void ButtonDownVoteViewOnClick(View v, int position) {
//to be implemented later later
    }


    //--------------------------------
}


//    public void getQuestionsFromServer() {
//
//        //to do--->
//        // load 10 quest at a time and the every time user scroll more --->more questions should appear
//
//        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage("Loading questions");
//        progressDialog.show();
//        String year;
//        int subjectId;
//        QuestionRequest questionRequest = new QuestionRequest();
//         QuestionLoader questionLoader = ServiceGenerator.createService(QuestionLoader.class);
//        Call<List<Question>> call = questionLoader.loadQuestions(questionRequest);
//        call.enqueue(new Callback<List<Question>>() {
//            @Override
//            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
//                questions=response.body();
//                Log.d(TAG, "on response is called ------questions ----> question_id--->" +questions.get(0).getQuestion_id()+"|| unique_id----> "+questions.get(0).getUnique_id());
//
//
//                questionsRecyclerAdapter = new QuestionsRecyclerAdapter(getActivity(), questions, MainActivity.unique_id, new ButtonVoteListener() {
//                    @Override
//                    public void ButtonUpVoteOnClick(View v, int position) {
//
//                        Log.d(TAG,"item position is "+ position);
//                        vote_2(questions.get(position));
//
//
//                        RecyclerView.ViewHolder vh=recyclerView.findViewHolderForAdapterPosition(position);
//                       // if it is not pressed
//                   if ( !vh.itemView.isPressed()){
//                        v.setBackground(getResources().getDrawable(R.drawable.likegreen_btn));
//                        vh.itemView.setPressed(true);
//
//                        Log.d(TAG," likegreen is set pressed -> "+ vh.itemView.isPressed() );
//                       int  votes= Integer.parseInt(((Button) v).getText().toString());
//
//                       ( (Button)v).setText((votes+1)+"");
//                    }
//                    else {
//                        v.setBackground(getResources().getDrawable(R.drawable.like_btn));
//                       vh.itemView.setPressed(false);
//                        Log.d(TAG, " like  is set pressed -> " + vh.itemView.isPressed());
//                       int  votes= Integer.parseInt(((Button) v).getText().toString());
//
//                       ( (Button)v).setText((votes-1)+"");
//
//                   }
//                    }
//
//                    @Override
//                    public void ButtonDownVoteViewOnClick(View v, int position) {
//
//                    }
//                });
//
//                if(recyclerView!=null)
//                recyclerView.setAdapter(questionsRecyclerAdapter);
//                animate(parent);
//
//                questionsRecyclerAdapter.notifyDataSetChanged();
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<List<Question>> call, Throwable t) {
//                Log.d(TAG, "on error is called");
//                Toast.makeText(getActivity(), "error loading questions", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//
//
//    }


//  boolean addQuestion(String content) {
//
//
//        Question question=new Question();
//        question.setAcademic_year(0);
//        question.setUnique_id(MainActivity.getUnique_id());
//        question.setCourse("java");
//        question.setQuestion(content);
//
//        QuestionPost questionPost = ServiceGenerator.createService(QuestionPost.class);
//        Log.d(TAG, "to send to server  ----> " + question.getQuestion());
//
//        Call<Respond> call = questionPost.postQuestion(question);
//
//        call.enqueue(new Callback<Respond>() {
//            @Override
//            public void onResponse(Call<Respond> call, Response<Respond> response) {
//                Log.d(TAG, "message from server ----> " + response.body().getMessage());
//            }
//
//            @Override
//            public void onFailure(Call<Respond> call, Throwable t) {
//                Log.d(TAG, "on error is called  ----> " + t.getLocalizedMessage());
//            }
//        });
//
//        return true;
//    }


//
//    void vote_2(Question question){
//
//        Log.d(TAG,"vote_t is trying to upvote q_id-----> "+question.getQuestion_id());
//        VoteRequest voteRequest=new VoteRequest();
//        voteRequest.setQuestion(question);
//        voteRequest.setCommand(VoteRequest.INCREASE_UP_VOTES);
//        voteRequest.setCode("ss");
//        VoteApi questionPost=ServiceGenerator.createService(VoteApi.class);
//        Call<Respond> call=questionPost.upVote(voteRequest);
//        call.enqueue(new Callback<Respond>() {
//            @Override
//            public void onResponse(Call<Respond> call, Response<Respond> response) {
//                Log.d(TAG,response.body().getMessage());
//            }
//
//            @Override
//            public void onFailure(Call<Respond> call, Throwable t) {
//                Log.d(TAG,t.getLocalizedMessage());
//
//            }
//        });
//
//    }

//to be put inside onClick upvote
//    boolean vote(Question question,View view){
//        upVoteState=UpVoteState.getUpVoteState();
//        VoteRequest voteRequest=new VoteRequest();
//        voteRequest.setQuestion(question);
//        voteRequest.setCode(code);


//        QuestionPost questionPost=ServiceGenerator.createService(QuestionPost.class);
//        Call<Respond> call=questionPost.upVote(voteRequest);
//        call.enqueue(new Callback<Respond>() {
//         @Override
//         public void onResponse(Call<Respond> call, Response<Respond> response) {
//             Log.d(TAG,response.body().getMessage());
//         }
//
//         @Override
//         public void onFailure(Call<Respond> call, Throwable t) {
//             Log.d(TAG,t.getLocalizedMessage());
//
//         }
//     });


//  return false;
//}
