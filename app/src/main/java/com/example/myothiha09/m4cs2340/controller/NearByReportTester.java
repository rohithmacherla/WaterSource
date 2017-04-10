package com.example.myothiha09.m4cs2340.controller;

import com.example.myothiha09.m4cs2340.model.Model;
import com.example.myothiha09.m4cs2340.model.OverallCondition;
import com.example.myothiha09.m4cs2340.model.WaterPurityReport;
import com.example.myothiha09.m4cs2340.model.WaterType;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by myothiha09 on 4/10/2017.
 */

public class NearByReportTester {
    private static final int TIMEOUT = 200;
    private ArrayList<WaterPurityReport> reports;

    @Before
    public void setup() {
        reports = new ArrayList<WaterPurityReport>();
    }

    @Test(timeout = TIMEOUT)
    public void testMethodWithValidData(){
        int number = 1;
        //no real date since only testing distance calculation
        reports.add(new WaterPurityReport("Date "+number++, number, "Reporter", "lat/Lng:(5.75, 20.08)", WaterType.BOTTLED, OverallCondition.SAFE, 20, 20));
    }
    @Test(timeout = TIMEOUT)
    public void testMethodWithInValidData(){

    }
    @Test(timeout = TIMEOUT)
    public void testMethodWithValidDataButNoNearBy(){

    }
}
