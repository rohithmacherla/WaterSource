package com.example.myothiha09.m4cs2340.test;

import com.example.myothiha09.m4cs2340.controller.GraphActivity;
import com.example.myothiha09.m4cs2340.model.OverallCondition;
import com.example.myothiha09.m4cs2340.model.WaterPurityReport;
import com.example.myothiha09.m4cs2340.model.WaterType;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Created by myothiha09 on 4/10/2017.
 */

public class MyoTests {
    private static final int TIMEOUT = 200;
    private ArrayList<WaterPurityReport> reports;

    @Before
    public void setup() {
        reports = new ArrayList<WaterPurityReport>();
    }

    @Test(timeout = TIMEOUT)
    public void testMethodWithValidInput() {
        int number = 1;
        //no real date since only testing distance calculation
        reports.add(new WaterPurityReport("Date "+number++, number, "Reporter", "lat/Lng:(5.75, 20.08)", WaterType.BOTTLED, OverallCondition.SAFE, 20, 20));
        ArrayList<WaterPurityReport> nearBy = GraphActivity.nearbyReports(5,20, reports);
        assertEquals(1, nearBy.size());
        reports.add(new WaterPurityReport("Date "+number++, number, "Reporter", "lat/Lng:(5.25, 20.08)", WaterType.BOTTLED, OverallCondition.SAFE, 20, 20));
        reports.add(new WaterPurityReport("Date "+number++, number, "Reporter", "lat/Lng:(55.25, 20.08)", WaterType.BOTTLED, OverallCondition.SAFE, 20, 20));
        nearBy = GraphActivity.nearbyReports(5.75, 20, reports);
        assertEquals(2, nearBy.size());
        nearBy = GraphActivity.nearbyReports(55.25, 20, reports);
        assertEquals(1, nearBy.size());
    }
    @Test(timeout = TIMEOUT)
    public void testMethodWithInvalidInput(){
        int number = 1;
        //no real date since only testing distance calculation
        reports.add(new WaterPurityReport("Date "+number++, number, "Reporter", "lat/Lng:(5.75, 20.08)", WaterType.BOTTLED, OverallCondition.SAFE, 20, 20));
        ArrayList<WaterPurityReport> nearBy;
        reports.add(new WaterPurityReport("Date "+number++, number, "Reporter", "lat/Lng:(5.25, 20.08)", WaterType.BOTTLED, OverallCondition.SAFE, 20, 20));
        nearBy = GraphActivity.nearbyReports(-91, 50, reports);
        assertNull(nearBy);
        nearBy = GraphActivity.nearbyReports(91, 50, reports);
        assertNull(nearBy);
        nearBy = GraphActivity.nearbyReports(0, -181, reports);
        assertNull(nearBy);
        nearBy = GraphActivity.nearbyReports(0, 181, reports);
        assertNull(nearBy);
        nearBy = GraphActivity.nearbyReports(0, 0, null);
        assertNull(nearBy);

    }
    @Test(timeout = TIMEOUT)
    public void testMethodWithValidInputButNoNearBy(){
        int number = 1;
        //no real date since only testing distance calculation
        reports.add(new WaterPurityReport("Date "+number++, number, "Reporter", "lat/Lng:(5.75, 20.08)", WaterType.BOTTLED, OverallCondition.SAFE, 20, 20));
        ArrayList<WaterPurityReport> nearBy;
        reports.add(new WaterPurityReport("Date "+number++, number, "Reporter", "lat/Lng:(5.25, 20.08)", WaterType.BOTTLED, OverallCondition.SAFE, 20, 20));
        reports.add(new WaterPurityReport("Date "+number++, number, "Reporter", "lat/Lng:(10.25, 14.08)", WaterType.BOTTLED, OverallCondition.SAFE, 20, 20));
        reports.add(new WaterPurityReport("Date "+number++, number, "Reporter", "lat/Lng:(54.25, 25.08)", WaterType.BOTTLED, OverallCondition.SAFE, 20, 20));
        nearBy = GraphActivity.nearbyReports(40, 50, reports);
        assertEquals(0, nearBy.size());
    }
}
