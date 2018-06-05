package com.projects.cactus.el_kollia.feed;

import com.projects.cactus.el_kollia.base.BasePresenter;
import com.projects.cactus.el_kollia.base.BaseView;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.QuestionRequest;
import com.projects.cactus.el_kollia.model.TAG;

import java.util.List;

/**
 * Created by el on 6/8/2017.
 */

public interface FeedContract {

    interface Presenter extends BasePresenter<View> {


        void getPosts(QuestionRequest questionRequest);

        void post(QuestionRequest questionRequest);

        //make an api call to search for this string in all questions
        void find(String string);

        //filter current displayed questions by tags
        void filter(String s);
    }


    interface View extends BaseView<Presenter> {


        void showPosts(List<Question> posts);

        void setupTags(List<TAG> tags);

        void openPostActivity(int questionId);

        void showFilteredData(String string);

        void onClickRetry();
    }
}
