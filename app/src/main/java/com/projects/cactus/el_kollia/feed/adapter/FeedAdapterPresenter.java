package com.projects.cactus.el_kollia.feed.adapter;

import android.support.v7.widget.RecyclerView;

import com.projects.cactus.el_kollia.like.LikeInteractorContract;
import com.projects.cactus.el_kollia.model.LikeResponse;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.util.AppConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Bishoy Abd on 3/24/2018.
 */


public class FeedAdapterPresenter implements FeedAdapterContract.Presenter {

    private List<Question> questions = Collections.emptyList();
    private List<Question> filteredQuestions = Collections.emptyList();

    private Updatable<List<Question>> updatableAdapter;
    private LikeInteractorContract likeInteractor;
    private FeedAdapterContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private FilterManager filterManager;

    @Inject
    public FeedAdapterPresenter(LikeInteractorContract likeInteractor) {
        this.likeInteractor = likeInteractor;
        filterManager = new FilterManager();
    }

    @Override
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        this.filteredQuestions = questions;

    }

    @Override
    public void subscribe(FeedAdapterContract.View view) {
        this.view = view;
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void onBindView(FeedAdapterContract.View holder, int position) {
        if (questions == null)
            throw new NullPointerException("questions must be set");
        Question question = filteredQuestions.get(position);
        holder.setDateAsked(question.getDate());
        holder.setUserName(question.getUser_name());
        holder.setUserThumbnail(question.getUser_profile_photo());
        holder.setQuestion(question.getQuestion());
        holder.markLiked(question.IsLiked());

    }

    @Override
    public void onClickLike(int position) {
        toggle(position); //toggle button to give the user an instant response
        tryToLike(position); // make api call to like/undoLike
    }


    /**
     * toggle button shape state
     *
     * @param position viewholder position
     */

    private void toggle(int position) {
        //if liked before, mark unLiked
        if (filteredQuestions.get(position).IsLiked()) {
            if (isViewAttached()) {
                view.markLiked(false);
                filteredQuestions.get(position).setIsLiked(false);
                Timber.d("liked before ,, canceling like");

                //if not liked before, mark liked
            }
        } else {
            if (isViewAttached()) {
                view.markLiked(true);
                filteredQuestions.get(position).setIsLiked(true);
                Timber.d("you liked !");
            }
        }

        updatableAdapter.update();

    }

    /**
     * make Api call to try to like / undoLike
     *
     * @param position
     */
    private void tryToLike(int position) {

        Disposable disposable = likeInteractor.doLike(filteredQuestions.get(position))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> process(response, position));

        compositeDisposable.add(disposable);


    }

    /**
     * handle like response -just for readability I created another method
     *
     * @param likeResponse
     * @param position
     */
    private void process(LikeResponse likeResponse, int position) {
        if (likeResponse.isSuccessful()) {
            if (isViewAttached()) {
                Timber.d("like success");
            }
        } else {
            Timber.d("like failed");
            if (isViewAttached()) {
                toggle(position);
            }
        }


        updatableAdapter.update();


    }

    /**
     * @return list size
     */

    @Override
    public int getItemCount() {
        return filteredQuestions.size();
    }

    @Override
    public void filter(String query) {
        //if user clicked all
        if (query.equals(AppConstants.ALL)) {
            filteredQuestions = questions;
            updatableAdapter.update();
            return;
        }
        filterManager.filter(questions, query, filteredList -> {
            filteredQuestions = new ArrayList<>(filteredList);
            Timber.d(filteredQuestions.toString());
            updatableAdapter.update(); //notify data changed
        });


    }


    private boolean isViewAttached() {
        return view != null;
    }


    /**
     * to encapsulate the call to {@link RecyclerView.Adapter notifyDataSetChanged()}
     * Adapter must implement {@link Updatable}
     *
     * @param updatableAdapter an implementation of {@link Updatable}
     */
    @Override
    public void setUpdatableAdapter(Updatable<List<Question>> updatableAdapter) {
        this.updatableAdapter = updatableAdapter;
    }


}
