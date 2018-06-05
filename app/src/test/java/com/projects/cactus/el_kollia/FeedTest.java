package com.projects.cactus.el_kollia;

import com.projects.cactus.el_kollia.feed.FeedContract;
import com.projects.cactus.el_kollia.feed.FeedInteractor;
import com.projects.cactus.el_kollia.feed.FeedPresenter;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.QuestionRequest;
import com.projects.cactus.el_kollia.util.TestSchedulerProvider;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Bishoy Abd on 6/2/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class FeedTest {


    @BeforeClass
    public static void onlyOnce() throws Exception {
    }


    /*
        At our test class, we will first declare our mock objects needed by the object under test (the presenter)
        as class attributes.
        The (two dependencies needed to be mocked are the view and the data source (FeedInteractor)).
    */

    @Mock
    FeedContract.View feedView;
    @Mock
    FeedInteractor feedInteractor;

    @Mock
    QuestionRequest questionRequest;

    FeedContract.Presenter feedPresenter;

    //this cannot be mocked so I have to use the builin RXJava TEstScheduler
//    @Mock
//    SchedulerProvider schedulerProvider;

    private TestScheduler mTestScheduler;
    private TestSchedulerProvider testSchedulerProvider;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mTestScheduler = new TestScheduler();
        testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);

        //Now we should instantiate the presenter passing the mocks as dependencies or do it on setup method:

        feedPresenter = new FeedPresenter(feedInteractor, testSchedulerProvider);
        feedPresenter.subscribe(feedView);
    }

    @Test
    public void shouldShowLoadingThenHideThenShowPosts() {

            /*
            The code above means that whenever the method getCharacters() is called
            return the CharactersResponseModel instance declared.
             */

        List<Question> list = testResults("");
        when(feedInteractor.getPosts(questionRequest)).thenReturn(Observable.just(list));
        // Next we call the getPosts method of our presenter interface which will allow us to write the test assertions.
        feedPresenter.getPosts(questionRequest);
        //this must be called inorder for the callback of onNext onComplete plupluh to be called ,o
        // otherwise the showloading and showpost will never be called
        mTestScheduler.triggerActions();
        //assures that method invocation are called in this order
        InOrder inOrder = Mockito.inOrder(feedView);
        verify(feedView, times(1)).showLoading();
        verify(feedView, times(1)).hideLoading();
        verify(feedView, times(1)).showPosts(list);
    }

    /*
    Since we want the unit test to be fast and to not rely on internet connection,
     we will tell the data source to return a fixed response.
     */
    private List<Question> testResults(String userID) {
        List<Question> questions = new ArrayList<>();

        Question question = new Question("uid1", "Big data", "12:12 am", false);
        questions.add(question);
        question = new Question("uid1", "Hi,\n" +
                "\n" +
                "Previously I was using unbind() method but now in v8 of ButterKnife, there's no such method and just the binding method returns an Unbinder which will help with that. But the question, what is the correct way to do that? Is it recommended in fragments?", "12:12 am", true);
        questions.add(question);
        question = new Question("uid1", "Hi,\n" +
                "\n" +
                "Previously I was using unbind() method but now in v8 of ButterKnife, there's no such method and just the binding method returns an Unbinder which will help with that. But the question, what is the correct way to do that? Is it recommended in fragments?", "12:12 am", false);
        questions.add(question);
        question = new Question("uid1", "Hi,\n" +
                "\n" +
                "Previously I was using unbind() method but now in v8 of ButterKnife, there's no such method and just the binding method returns an Unbinder which will help with that. But the question, what is the correct way to do that? Is it recommended in fragments?", "12:12 am", false);
        questions.add(question);
        question = new Question("uid1", "Hi,\n" +
                "\n" +
                "Previously I was using unbind() method but now in v8 of ButterKnife, there's no such method and just the binding method returns an Unbinder which will help with that. But the question, what is the correct way to do that? Is it recommended in fragments?", "12:12 am", true);
        questions.add(question);
        question = new Question("uid1", "java", "12:12 am", false);
        questions.add(question);
        question = new Question("uid1", "All", "12:12 am", true);
        questions.add(question);
        question = new Question("uid1", "Hi,\n" +
                "\n" +
                "Previously I was using unbind() method but now in v8 of ButterKnife, there's no such method and just the binding method returns an Unbinder which will help with that. But the question, what is the correct way to do that? Is it recommended in fragments?", "12:12 am", true);
        questions.add(question);

        return questions;
    }
}
