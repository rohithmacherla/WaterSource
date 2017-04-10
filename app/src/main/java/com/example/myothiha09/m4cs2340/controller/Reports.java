package com.example.myothiha09.m4cs2340.controller;

/*
  Created by myothiha09 on 3/28/2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import com.example.myothiha09.m4cs2340.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Reports extends Fragment implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener{


    private ViewPager viewPager;
    private TabHost tabHost;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.reports_container, container, false);
        initiateViewPager();
        initiateTabHost();
        return view;

    }

    /**
     * Add the fragments to the view pager
     */
    private void initiateViewPager(){
        List<Fragment> list = new ArrayList<>();
        viewPager =   (ViewPager) view.findViewById(R.id.myViewPager);
        list.add(new ReportFragment());
        list.add(new PurityFragment());

        MyFragmentAdapter fm = new MyFragmentAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(fm);
        viewPager.setOnPageChangeListener(this);
    }

    /**
     * Set the titles for view pager.
     */
    private void initiateTabHost() {
        tabHost = (TabHost) view.findViewById(R.id.tabHost);
        tabHost.setup();
        String[] tabName = {"Water Source Report", "Water Purity Report"};


        //Necessary Steps for creating TabSpec
        for (String name : tabName) {
            TabHost.TabSpec sp = tabHost.newTabSpec(name);
            sp.setIndicator(name);
            sp.setContent(new MyContent(getActivity().getApplicationContext()));
            tabHost.addTab(sp);
        }
        tabHost.setOnTabChangedListener(this);
    }


    @Override
    public void onTabChanged(String tabId) {
        int currentTab = tabHost.getCurrentTab();
        viewPager.setCurrentItem(currentTab);
        HorizontalScrollView hsv = (HorizontalScrollView)view.findViewById(R.id.myScrollView);
        View tabView = tabHost.getCurrentTabView();
        int x = tabView.getLeft()-((hsv.getWidth() - tabView.getWidth()));
        hsv.smoothScrollTo(x,0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class MyContent implements TabHost.TabContentFactory{
        final Context context;
        public MyContent(Context context){
            this.context = context;
        }


        //automatically called when an object of MyContent is created
        @Override
        public View createTabContent(String tag) {
            View v = new View(context);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        tabHost.setCurrentTab(0);
    }
}


