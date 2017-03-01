package com.example.myothiha09.m4cs2340.model;

import java.util.ArrayList;
/**
 * Created by vgiridhar on 3/1/17.
 */

public class Model {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<WaterSourceReport> waterSourceReports = new ArrayList<>();
    int reportNumber = 0;

    public Model() {

    }

    public void addWaterReport(WaterSourceReport w) {
        waterSourceReports.add(w);
    }

    public int getReportNumber() {
        return (++reportNumber);
    }
}
