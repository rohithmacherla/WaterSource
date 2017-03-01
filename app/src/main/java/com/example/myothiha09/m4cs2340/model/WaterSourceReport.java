package com.example.myothiha09.m4cs2340.model;
import java.util.ArrayList;

/**
 * Created by vgiridhar on 2/26/17.
 */

public class WaterSourceReport {
    private String dateAndTime;
    private int reportNumber;
    private String reporterName;
    private String waterLocation;
    private WaterType waterType;
    private WaterCondition waterCondition;


    public WaterSourceReport(String dt, int rNum, String rName,
                             String wL, WaterType wT, WaterCondition wC) {
        this.dateAndTime = dt;
        this.reportNumber = rNum;
        this.reporterName = rName;
        this.waterLocation = wL;
        this.waterType = wT;
        this.waterCondition = wC;
    }

    //empty constructor
    public WaterSourceReport() {
        this.dateAndTime = "";
        this.reportNumber = 0;
        this.reporterName = "";
        this.waterLocation = "";
        this.waterType = WaterType.BOTTLED;
        this.waterCondition = WaterCondition.POTABLE;
    }

    public String getDateAndTime() {
        return this.dateAndTime;
    }

    public void setDateAndTime(String dt) {
        dateAndTime = dt;
    }

    public int getReportNumber() {
        return this.reportNumber;
    }

    public void setReportNumber(int rNum) {
        reportNumber = rNum;
    }

    public String getReporterName() {
        return this.reporterName;
    }

    public void setReporterName(String rName) {
        reporterName = rName;
    }

    public String getWaterLocation() {
        return this.waterLocation;
    }

    public void setWaterLocation(String wL) {
        waterLocation = wL;
    }

    public WaterType getWaterType() {
        return this.waterType;
    }

    public void setWaterType(WaterType wT) {
        waterType = wT;
    }

    public WaterCondition getWaterCondition() {
        return this.waterCondition;
    }

    public void setWaterConditionW(WaterCondition wC) {
        waterCondition = wC;
    }




}