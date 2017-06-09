package com.projects.cactus.el_kollia.feed.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.projects.cactus.el_kollia.Activity.NotificationFragment;
import com.projects.cactus.el_kollia.Activity.OtherFragment;
import com.projects.cactus.el_kollia.Activity.ProfileFragment;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.adapters.MainTabsPagerAdapter;
import com.projects.cactus.el_kollia.feed.view.FeedFragment;
import com.projects.cactus.el_kollia.util.Util;

/**
 * Created by Bishoy  on 4/8/2017.
 */






public class MainActivity extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager viewPager;
    MainTabsPagerAdapter mainTabsPagerAdapter;
    static String unique_id;
    int x;
    private String TAG="MainActivity";


    public static String getUnique_id() {
        return unique_id;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        prepareRPager();
        unique_id=getIntent().getStringExtra(Util.KEY_USER_ID);
        if (unique_id!=null)
            Log.d(TAG,"unique id is = "+unique_id);




    }


    void prepareRPager(){
        tabLayout= (TabLayout) findViewById(R.id.tab_layout_main_id);
        viewPager= (ViewPager) findViewById(R.id.main_activity_viewpager_id);
        mainTabsPagerAdapter=new MainTabsPagerAdapter(getSupportFragmentManager(),this);
        mainTabsPagerAdapter.addFragment(new FeedFragment(),"FEED");
        mainTabsPagerAdapter.addFragment(new NotificationFragment(),"FEED");
        mainTabsPagerAdapter.addFragment(new OtherFragment(),"Other");
        mainTabsPagerAdapter.addFragment(new ProfileFragment(),"Profile");
        //add tab icons





        tabLayout.setupWithViewPager(viewPager);


        viewPager.setAdapter(mainTabsPagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.newsfeed_tab);
        tabLayout.getTabAt(1).setIcon(R.drawable.notification_tab);
        tabLayout.getTabAt(2).setIcon(R.drawable.other_tab);
        tabLayout.getTabAt(3).setIcon(R.drawable.profile_tab);
        //initial alpha
        tabLayout.getTabAt(0).getIcon().setAlpha(255);
        tabLayout.getTabAt(1).getIcon().setAlpha(128);
        tabLayout.getTabAt(2).getIcon().setAlpha(128);
        tabLayout.getTabAt(3).getIcon().setAlpha(128);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabLayout.getTabAt(0).getIcon().setAlpha(255);
                        tabLayout.getTabAt(1).getIcon().setAlpha(128);
                        tabLayout.getTabAt(2).getIcon().setAlpha(128);
                        tabLayout.getTabAt(3).getIcon().setAlpha(128);

                        break;
                    case 1:
                        tabLayout.getTabAt(0).getIcon().setAlpha(128);
                        tabLayout.getTabAt(1).getIcon().setAlpha(255);
                        tabLayout.getTabAt(2).getIcon().setAlpha(128);
                        tabLayout.getTabAt(3).getIcon().setAlpha(128);

                        break;
                    case 2:
                        tabLayout.getTabAt(0).getIcon().setAlpha(128);
                        tabLayout.getTabAt(1).getIcon().setAlpha(128);
                        tabLayout.getTabAt(2).getIcon().setAlpha(255);
                        tabLayout.getTabAt(3).getIcon().setAlpha(128);

                        break;
                    case 3:
                        tabLayout.getTabAt(0).getIcon().setAlpha(128);
                        tabLayout.getTabAt(1).getIcon().setAlpha(128);
                        tabLayout.getTabAt(2).getIcon().setAlpha(128);
                        tabLayout.getTabAt(3).getIcon().setAlpha(255);

                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        super.onStop();

    }
}
