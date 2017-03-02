package com.example.myothiha09.m4cs2340.model;

import java.util.ArrayList;
/**
 * Created by vgiridhar on 3/1/17.
 */

public class Model {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<WaterSourceReport> waterSourceReports = new ArrayList<>();
    int reportNumber = 0;

    private static Model model;

    private Model() {

    }
    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public void addWaterReport(WaterSourceReport w) {
        waterSourceReports.add(w);
    }

    public int getReportNumber() {
        return (++reportNumber);
    }

    public ArrayList<WaterSourceReport> getWaterSourceReports() {
        return waterSourceReports;
    }
}
