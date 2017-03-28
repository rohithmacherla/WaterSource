package com.example.myothiha09.m4cs2340.controller;

/**
 * Created by myothiha09 on 3/28/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by myothiha09 on 3/5/16.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> myList;
    public MyFragmentAdapter(FragmentManager fm, List<Fragment> myList){
        super(fm);
        this.myList = myList;
    }
    @Override
    public Fragment getItem(int position) {
        return myList.get(position);
    }

    @Override
    public int getCount() {
        return myList.size();
    }
}
