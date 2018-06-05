package com.projects.cactus.el_kollia.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.adapters.MainTabsPagerAdapter;
import com.projects.cactus.el_kollia.feed.FeedFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bishoy Abd on 5/5/2018.
 */
public class HomeFragment extends Fragment implements MainActivity.FragmentStringCommunicator {
    //tabs
    @BindView(R.id.tab_layout_main_id)
    TabLayout tabLayout;
    @BindView(R.id.main_activity_viewpager_id)
    ViewPager viewPager;
    MainTabsPagerAdapter mainTabsPagerAdapter;

    private MainActivity.FragmentStringCommunicator fragmentStringCommunicator;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        preparePager();
        return view;

    }




    private void preparePager() {
        mainTabsPagerAdapter = new MainTabsPagerAdapter(getChildFragmentManager(), getActivity());
        mainTabsPagerAdapter.addFragment(new FeedFragment(), "FEED");
        //replace with bookmarked fragment
        mainTabsPagerAdapter.addFragment(new NotificationFragment(), "Bookmarks");
        mainTabsPagerAdapter.addFragment(new OtherFragment(), "Other");
        //add tab icons

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(mainTabsPagerAdapter);

//        tabLayout.getTabAt(0).setIcon(R.drawable.newsfeed_tab);
//        tabLayout.getTabAt(1).setIcon(R.drawable.notification_tab);
//        tabLayout.getTabAt(2).setIcon(R.drawable.profile_tab);

//        //initial alpha
//        tabLayout.getTabAt(0).getIcon().setAlpha(255);
//        tabLayout.getTabAt(1).getIcon().setAlpha(128);
//        tabLayout.getTabAt(2).getIcon().setAlpha(128);

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                switch (position) {
//                    case 0:
//                        tabLayout.getTabAt(0).getIcon().setAlpha(255);
//                        tabLayout.getTabAt(1).getIcon().setAlpha(128);
//                        tabLayout.getTabAt(2).getIcon().setAlpha(128);
//
//                        break;
//                    case 1:
//                        tabLayout.getTabAt(0).getIcon().setAlpha(128);
//                        tabLayout.getTabAt(1).getIcon().setAlpha(255);
//                        tabLayout.getTabAt(2).getIcon().setAlpha(128);
//
//                        break;
//                    case 2:
//                        tabLayout.getTabAt(0).getIcon().setAlpha(128);
//                        tabLayout.getTabAt(1).getIcon().setAlpha(128);
//                        tabLayout.getTabAt(2).getIcon().setAlpha(128);
//
//                        break;
//
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


    }

    @Override
    public void notifyFragment(String string) {
        if (fragmentStringCommunicator != null)
            fragmentStringCommunicator.notifyFragment(string);
    }


    public void setFragmentStringCommunicator(MainActivity.FragmentStringCommunicator fragmentStringCommunicator) {
        this.fragmentStringCommunicator = fragmentStringCommunicator;
    }
}
