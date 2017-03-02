package com.example.myothiha09.m4cs2340.controller;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.Model;
import com.example.myothiha09.m4cs2340.model.WaterSourceReport;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends ListFragment {
    CustomArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.water_report_listview, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Model model = Model.getInstance();
        ArrayList<WaterSourceReport> report = model.getWaterSourceReports();
        adapter = new CustomArrayAdapter(getActivity(), report);
        setListAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
