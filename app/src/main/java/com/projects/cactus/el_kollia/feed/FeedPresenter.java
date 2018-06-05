package com.projects.cactus.el_kollia.feed;

import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.QuestionRequest;
import com.projects.cactus.el_kollia.util.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by el on 6/8/2017.
 */

public class FeedPresenter implements FeedContract.Presenter {

    private FeedContract.View feedView;
    private FeedInteractor feedInteractor;
    private SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public FeedPresenter(FeedInteractor feedInteractor, SchedulerProvider schedulerProvider) {
        this.feedInteractor = feedInteractor;
        this.schedulerProvider = schedulerProvider;
    }


    @Override
    public void getPosts(QuestionRequest questionRequest) {
        compositeDisposable.clear();
        feedView.showLoading();
        Disposable disposable = feedInteractor.getPosts(questionRequest)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableObserver<List<Question>>() {
                    @Override
                    public void onNext(List<Question> questions) {
                        feedView.hideLoading();
                        feedView.hideError();
                        Timber.d("questions ---> " + questions.toString());
                        feedView.showPosts(questions);
                    }

                    @Override
                    public void onError(Throwable e) {
                        feedView.showError();
                        Timber.d("error getting questions!");
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        compositeDisposable.add(disposable);
    }


    @Override
    public void post(QuestionRequest questionRequest) {
        feedInteractor.post(questionRequest);
    }

    @Override
    public void find(String string) {
        //either search in the cloud or just filter current questions
        feedView.showFilteredData(string);

    }

    @Override
    public void filter(String s) {
        feedView.showFilteredData(s);
    }


    @Override
    public void subscribe(FeedContract.View view) {
        this.feedView = view;
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();

    }
}
