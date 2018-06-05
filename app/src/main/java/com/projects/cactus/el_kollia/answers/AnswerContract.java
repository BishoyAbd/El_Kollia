package com.projects.cactus.el_kollia.answers;

import com.projects.cactus.el_kollia.base.BasePresenter;
import com.projects.cactus.el_kollia.model.Answer;
import com.projects.cactus.el_kollia.model.Question;

import java.util.List;

/**
 * Created by Bishoy Abd on 6/1/2018.
 */
public interface AnswerContract {

    interface presenter extends BasePresenter<View> {

        void loadQuestion(String questionId);

        void loadAnswers(String questionId);

        void followQuestion(String qustionId);

        void openAnswerFragment();

        void passQuestion(String questionId);

        void upVoteQuestion(String questionId);

    }

    interface View {


        void showQuestion(Question question);

        void showAnswers(List<Answer> answers);


    }

}
