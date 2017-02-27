package com.example.myothiha09.m4cs2340.model;

/**
 * Created by vgiridhar on 2/26/17.
 */

public class WaterSourceReport {
    private String date;
    private String time;
    private String reportNumber;
    private String reporterName;
    private String waterLocation;
    private WaterType waterType;
    private WaterCondition waterCondition;

    public WaterSourceReport(String d, String t, String rN1, String rN2,
                             String wL, WaterType wT, WaterCondition wC) {
        this.date = d;
        this.time = t;
        this.reportNumber = rN1;
        this.reporterName = rN2;
        this.waterLocation = wL;
        this.waterType = wT;
        this.waterCondition = wC;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String d) {
        date = d;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String t) {
        time = t;
    }

    public String getReportNumber() {
        return this.reportNumber;
    }

    public void setReportNumber(String rN1) {
        reportNumber = rN1;
    }

    public String getReporterName() {
        return this.reporterName;
    }

    public void setReporterName(String rN2) {
        reporterName = rN2;
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