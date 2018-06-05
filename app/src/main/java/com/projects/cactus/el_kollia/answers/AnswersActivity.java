package com.projects.cactus.el_kollia.answers;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.adapters.AnswerRecyclerAdapter;
import com.projects.cactus.el_kollia.base.ErrorLoadingActivity;
import com.projects.cactus.el_kollia.model.Answer;
import com.projects.cactus.el_kollia.model.Question;

import java.util.List;

/**
 * Created by Bishoy Abd on 5/4/2018.
 */

public class AnswersActivity extends ErrorLoadingActivity implements AnswerContract.View {


    private AnswerContract.presenter presenter;
    private AnswerRecyclerAdapter l;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initPresenter() {
        super.initPresenter();

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_answers;
    }

    @Override
    protected int getErrorViewResId() {
        return R.id.error_view_root;
    }


    private void prepareRecyclerView() {

    }

    @Override
    public void showQuestion(Question question) {

    }

    @Override
    public void showAnswers(List<Answer> answers) {

    }
}
