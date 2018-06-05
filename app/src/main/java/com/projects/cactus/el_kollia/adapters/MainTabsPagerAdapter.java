package com.projects.cactus.el_kollia.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by el on 4/15/2017.
 */
public class MainTabsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> pages;
    private List<String> titles;
    private Context ctx;


    public MainTabsPagerAdapter(FragmentManager fm, Context context, List<Fragment> pages, List<String> titles) {
        super(fm);
        this.pages = pages;
        this.titles = titles;
        this.ctx = context;
    }

    public MainTabsPagerAdapter(FragmentManager fm, Context context) {

        super(fm);
        pages = new ArrayList<>();
        titles = new ArrayList<>();
        this.ctx = context;

    }


    public void addFragment(Fragment newFrag, String newTitle) {
        pages.add(newFrag);
        titles.add(newTitle);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//
//        Drawable drawable= ContextCompat.getDrawable(ctx,resId[position]);
//        drawable.setBounds(0,0,drawable.getIntrinsicWidth()/4,drawable.getIntrinsicHeight()/4);
//        SpannableString sb=new SpannableString(" ");
//        ImageSpan imageSpan=new ImageSpan(drawable,ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan,0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;
//    }
}
