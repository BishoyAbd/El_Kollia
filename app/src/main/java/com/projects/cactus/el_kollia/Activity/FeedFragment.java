package com.projects.cactus.el_kollia.Activity;

import android.animation.Animator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.QuoteSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.projects.cactus.el_kollia.ApiServices.QuestionLoader;
import com.projects.cactus.el_kollia.ApiServices.QuestionPost;
import com.projects.cactus.el_kollia.ApiServices.ServiceGenerator;
import com.projects.cactus.el_kollia.ApiServices.VoteApi;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.adapters.ButtonVoteListener;
import com.projects.cactus.el_kollia.adapters.QuestionsRecyclerAdapter;
import com.projects.cactus.el_kollia.adapters.RecyclerClickListener;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.QuestionRequest;
import com.projects.cactus.el_kollia.model.Respond;
import com.projects.cactus.el_kollia.model.VoteRequest;
import com.projects.cactus.el_kollia.state.QuestionStateWrapper;
import com.projects.cactus.el_kollia.state.State;
import com.projects.cactus.el_kollia.state.UpVoteState;
import com.projects.cactus.el_kollia.util.Util;
import com.twotoasters.jazzylistview.JazzyEffect;
import com.twotoasters.jazzylistview.effects.CardsEffect;
import com.twotoasters.jazzylistview.effects.CurlEffect;
import com.twotoasters.jazzylistview.effects.FadeEffect;
import com.twotoasters.jazzylistview.effects.FlyEffect;
import com.twotoasters.jazzylistview.effects.HelixEffect;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.ContinuousSort;
import com.willowtreeapps.spruce.sort.CorneredSort;
import com.willowtreeapps.spruce.sort.DefaultSort;
import com.willowtreeapps.spruce.sort.LinearSort;
import com.willowtreeapps.spruce.sort.RadialSort;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.button;


/**
 * Created by el on 4/14/2017.
 */

public class FeedFragment extends Fragment implements QuestionDialog.OnDialogButtonClick  {

    FloatingActionButton addQuestion;
    SwipeRefreshLayout swipeRefreshLayout;
    private static final String TAG = "FeedFragment";
    QuestionsRecyclerAdapter questionsRecyclerAdapter;
    RecyclerView recyclerView;

    Button upVote_btn,downVote_btn,showCmt_btn;
    ViewGroup parent;

    private List<Question> questions=new ArrayList<>();


    State upVoteState;
    QuestionStateWrapper questionStateWrapper=new QuestionStateWrapper();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstance) {
        QuestionDialog questionDialog = new QuestionDialog();
        questionDialog.setTargetFragment(this, 0);
        View view = inflater.inflate(R.layout.feed_fragment, viewGroup, false);

        parent= viewGroup;

        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swip_refresh_get_questions);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getQuestionsFromServer();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        addQuestion = (FloatingActionButton) view.findViewById(R.id.add_quest_fab_id);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionDialog questionDialog = new QuestionDialog();
                questionDialog.setTargetFragment(FeedFragment.this, 0);
                new QuestionDialog().show(getActivity().getSupportFragmentManager(), "QuestionDialog");
            }
        });

        prepareQuestions(view);
        return view;

    }


    void initializeViews(View view){
        upVote_btn= (Button) view.findViewById(R.id.button_upVote_inPost_id);
        downVote_btn= (Button) view.findViewById(R.id.button_downVote_inPost_id);
        showCmt_btn= (Button) view.findViewById(R.id.button_show_comments_inPost_id);


    }


    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
getQuestionsFromServer();
    }

    void prepareQuestions(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.questions_recyclerView_id);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(questionsRecyclerAdapter);
        JazzyRecyclerViewScrollListener jazzyRecyclerViewScrollListener = new JazzyRecyclerViewScrollListener();
        jazzyRecyclerViewScrollListener.setTransitionEffect(new CardsEffect());
        recyclerView.setOnScrollListener(jazzyRecyclerViewScrollListener);

        recyclerView.addOnItemTouchListener(
                new RecyclerClickListener(getActivity(), recyclerView ,new RecyclerClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                         int qId=questions.get(position).getQuestion_id();
                        openPostActivity(qId);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })


        );




    }

    private void openPostActivity(int qId) {

        Intent intent=new Intent(getActivity(),PostActivity.class);
        intent.putExtra(Util.QUESTION_ID_EXRA,qId);
        startActivity(intent);
    }

    public void getQuestionsFromServer() {

        //to do--->
        // load 10 quest at a time and the every time user scroll more --->more questions should appear

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading questions");
        progressDialog.show();
        String year;
        int subjectId;
        QuestionRequest questionRequest = new QuestionRequest();
         QuestionLoader questionLoader = ServiceGenerator.createService(QuestionLoader.class);
        Call<List<Question>> call = questionLoader.loadQuestions(questionRequest);
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                questions=response.body();
                Log.d(TAG, "on response is called ------questions ----> question_id--->" +questions.get(0).getQuestion_id()+"|| unique_id----> "+questions.get(0).getUnique_id());


                questionsRecyclerAdapter = new QuestionsRecyclerAdapter(getActivity(), questions,MainActivity.unique_id, new ButtonVoteListener() {
                    @Override
                    public void ButtonUpVoteOnClick(View v, int position) {

                        Log.d(TAG,"item position is "+ position);
                        vote_2(questions.get(position));


                        RecyclerView.ViewHolder vh=recyclerView.findViewHolderForAdapterPosition(position);
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
                    public void ButtonDownVoteViewOnClick(View v, int position) {

                    }
                });

                if(recyclerView!=null)
                recyclerView.setAdapter(questionsRecyclerAdapter);
                animate(parent);

                questionsRecyclerAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Log.d(TAG, "on error is called");
                Toast.makeText(getActivity(), "error loading questions", Toast.LENGTH_SHORT).show();


            }
        });


    }

    private void animate(ViewGroup viewGroup) {

        Animator spruceAnimator = new Spruce
                .SpruceBuilder(viewGroup)
                .sortWith(new LinearSort(500L,false, LinearSort.Direction.TOP_TO_BOTTOM))
                .animateWith(new Animator[] {DefaultAnimations.shrinkAnimator(viewGroup,800L)})
                .start();
    }

    boolean addQuestion(String content) {


        Question question=new Question();
        question.setAcademic_year(0);
        question.setUnique_id(MainActivity.getUnique_id());
        question.setCourse("java");
        question.setQuestion(content);

        QuestionPost questionPost = ServiceGenerator.createService(QuestionPost.class);
        Log.d(TAG, "to send to server  ----> " + question.getQuestion());

        Call<Respond> call = questionPost.postQuestion(question);

        call.enqueue(new Callback<Respond>() {
            @Override
            public void onResponse(Call<Respond> call, Response<Respond> response) {
                Log.d(TAG, "message from server ----> " + response.body().getMessage());
            }

            @Override
            public void onFailure(Call<Respond> call, Throwable t) {
                Log.d(TAG, "on error is called  ----> " + t.getLocalizedMessage());
            }
        });

        return true;
    }


    @Override
    public void onClickPost(String content) {
        Log.d(TAG, "uploading");
        addQuestion(content);
    }

    @Override
    public void onClickCancel() {

    }


    //when dislike is pressed
    void changeButtonToDislike(){



    }

    //when like pressed
    void changeButtonToLike(){

    }


    void vote_2(Question question){

        Log.d(TAG,"vote_t is trying to upvote q_id-----> "+question.getQuestion_id());
        VoteRequest voteRequest=new VoteRequest();
        voteRequest.setQuestion(question);
        voteRequest.setCommand(VoteRequest.INCREASE_UP_VOTES);
        voteRequest.setCode("ss");
        VoteApi questionPost=ServiceGenerator.createService(VoteApi.class);
        Call<Respond> call=questionPost.upVote(voteRequest);
        call.enqueue(new Callback<Respond>() {
         @Override
         public void onResponse(Call<Respond> call, Response<Respond> response) {
             Log.d(TAG,response.body().getMessage());
         }

         @Override
         public void onFailure(Call<Respond> call, Throwable t) {
             Log.d(TAG,t.getLocalizedMessage());

         }
     });

    }

//to be put inside onClick upvote
    boolean vote(Question question,View view){
        upVoteState=UpVoteState.getUpVoteState();
        VoteRequest voteRequest=new VoteRequest();
        voteRequest.setQuestion(question);
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





        return false;
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
}

