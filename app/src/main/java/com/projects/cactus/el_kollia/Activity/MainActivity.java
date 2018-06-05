package com.projects.cactus.el_kollia.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.profile.view.ProfileFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Bishoy Abd on 5/5/2018.
 * contains 3 fragments controlled by BottomNavigationView [ {@link  HomeFragment } | {@link NotificationFragment} | {@link ProfileFragment }]
 * avoided transition as per design specs
 */
public class MainActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public interface FragmentStringCommunicator {
        void notifyFragment(String string);
    }


    FragmentStringCommunicator fragmentStringCommunicator;

    @BindView(R.id.home_container_fl)
    FrameLayout homeContainerFl;
    @BindView(R.id.home_bnv)
    BottomNavigationView homeBnv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private SearchView searchView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_t);
        ButterKnife.bind(this);
        setupBottomNavigation();
        setToolbar("");
        registerListeners();
        showFragment(new HomeFragment());

    }

    private void setupBottomNavigation() {
        homeBnv.setOnNavigationItemSelectedListener(item ->
                {
                    Fragment fragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.navigation_notification:
                            fragment = new NotificationFragment();
                            break;
                        case R.id.navigation_profile:
                            fragment = new ProfileFragment();
                            break;
                    }
                    showFragment(fragment);
                    return true;
                }
        );

    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container_fl, fragment).commit();
    }


    public void setFragmentStringCommunicator(FragmentStringCommunicator fragmentStringCommunicator) {
        this.fragmentStringCommunicator = fragmentStringCommunicator;
    }

    private void setToolbar(String title) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

    }


    public void registerListeners() {
        Disposable search = RxView.clicks(findViewById(R.id.searchBrn)).debounce(100, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    Toast.makeText(this, "openeing search", Toast.LENGTH_LONG).show();
                });

        Disposable ask = RxView.clicks(findViewById(R.id.addQuestionBtn)).debounce(100, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    Toast.makeText(this, "openeing add question", Toast.LENGTH_LONG).show();
                });

        compositeDisposable.addAll(ask, search);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}


