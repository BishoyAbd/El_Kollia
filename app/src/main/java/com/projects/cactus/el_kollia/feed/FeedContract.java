package com.projects.cactus.el_kollia.feed;

import android.support.annotation.StringRes;

import com.projects.cactus.el_kollia.base.BasePresenter;
import com.projects.cactus.el_kollia.base.BaseView;
import com.projects.cactus.el_kollia.model.Question;

import java.util.List;

/**
 * Created by el on 6/8/2017.
 */

public interface FeedContract {

    interface Presenter extends BasePresenter<View> {


        void getPosts(String userId);

        void post(String userId, String post);

        void refresh();

        void upvote(int questioId, String userId);
    }


    interface View extends BaseView<Presenter> {

        void showUpvoteSuccess();

        void showUpvoteFailure();

        void showUpvoteFailure(String error);

        void showUpvoteFailure(@StringRes int ResId);

        void showPosts(List<Question> posts);

        void openPostActivity(int qId);

    }
}
