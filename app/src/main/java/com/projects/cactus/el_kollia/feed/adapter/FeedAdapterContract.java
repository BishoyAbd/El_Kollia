package com.projects.cactus.el_kollia.feed.adapter;

import com.projects.cactus.el_kollia.base.BasePresenter;
import com.projects.cactus.el_kollia.model.Question;

import java.util.List;

/**
 * Created by Bishoy Abd on 3/24/2018.
 */

public interface FeedAdapterContract {

    interface Presenter extends BasePresenter<View> {


        void setUpdatableAdapter(Updatable<List<Question>> updatableAdapter);

        void setQuestions(List<Question> questions);

        void onBindView(View holder, int position);

        void onClickLike(int position);

        int getItemCount();

        void filter(String query);


    }

    interface View {

        void setUserName(String userName);

        void setUserThumbnail(String url);

        void setDateAsked(String date);

        void setQuestion(String question);

        void setNumLikes(String numLikes);

        void markLiked(boolean liked);

        void setPresenter(Presenter presenter);


    }
}
