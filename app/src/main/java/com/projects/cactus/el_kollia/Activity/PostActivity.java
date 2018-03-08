package com.projects.cactus.el_kollia.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.projects.cactus.el_kollia.ApiServices.AnswerLoaderApi;
import com.projects.cactus.el_kollia.ApiServices.ServiceGenerator;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.adapters.AnswerRecyclerAdapter;
import com.projects.cactus.el_kollia.feed.QuestionDialog;
import com.projects.cactus.el_kollia.model.Answer;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//activity that contain post and it's comment
public class PostActivity extends AppCompatActivity {

    private static final String QID_EXTRA = "question_id";
    private FloatingActionButton fab;
    private Question question;
    private List<Answer> answerList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter answerRecyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String TAG = "PostActivity";
    private int questionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        questionId = getIntent().getIntExtra(Util.QUESTION_ID_EXRA, 0);
        initializeViews();
        prepareAnswers();
        Log.d(TAG, "q id---- ---> ansewe(0) --> " + questionId);

        if (questionId != 0)
            loadAnswers(questionId);
        else
            Toast.makeText(this, "error loading answers please refresh ", Toast.LENGTH_SHORT).show();

    }


    void prepareAnswers() {
        recyclerView = (RecyclerView) findViewById(R.id.answers_recyclerView_id);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        answerRecyclerAdapter = new AnswerRecyclerAdapter(answerList, this);
        recyclerView.setAdapter(answerRecyclerAdapter);

    }


    void initializeViews() {

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAnswerDialog();
            }
        });

    }

    private void openAnswerDialog() {
        QuestionDialog questionDialog = new QuestionDialog();
        questionDialog.show(PostActivity.this.getSupportFragmentManager(), "QuestionDialog");
    }


    void loadAnswers(int questionId) {

        AnswerLoaderApi answerLoaderApi = ServiceGenerator.createService(AnswerLoaderApi.class);
        Call<List<Answer>> call = answerLoaderApi.loadAnswers(String.valueOf(questionId));

        call.enqueue(new Callback<List<Answer>>() {
            @Override
            public void onResponse(Call<List<Answer>> call, Response<List<Answer>> response) {
                answerList = response.body();
                Log.d(TAG, "on response is called ---> ansewe(0) --> " + answerList.get(0).getAnswer());
                answerRecyclerAdapter = new AnswerRecyclerAdapter(answerList, PostActivity.this);
                recyclerView.setAdapter(answerRecyclerAdapter);
                Log.d(TAG, "on response is called ---> adapter item counts --> " + answerRecyclerAdapter.getItemCount());
                answerRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Answer>> call, Throwable t) {
                Log.d(TAG, "on error is called --->  " + t.getLocalizedMessage());

            }
        });


    }

}
