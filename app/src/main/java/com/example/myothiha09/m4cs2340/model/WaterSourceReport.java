package com.example.myothiha09.m4cs2340.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Team 27

/**
 * The POJO that represents a WaterSourceReport.
 */
public class WaterSourceReport {
    private String dateAndTime;
    private int reportNumber;
    private String reporterName;
    private String waterLocation;
    private WaterType waterType;
    private WaterCondition waterCondition;
    public static List<WaterType> waterTypeList = Arrays.asList(WaterType.values());
    public static List<WaterCondition> waterConditionList = Arrays.asList(WaterCondition.values());

    /**
     * Constructor for WaterSourceReport
     * @param dt the date and time of the object's creation
     * @param rNum the report number
     * @param rName the name of the user
     * @param wL the water location put in
     * @param wT the water type selected
     * @param wC the water condition selected
     */
    public WaterSourceReport(String dt, int rNum, String rName,
                             String wL, WaterType wT, WaterCondition wC) {
        this.dateAndTime = dt;
        this.reportNumber = rNum;
        this.reporterName = rName;
        this.waterLocation = wL;
        this.waterType = wT;
        this.waterCondition = wC;
    }

    /**
     * Empty Constructor for WaterSourceReport
     */
    public WaterSourceReport() {
        this.dateAndTime = "";
        this.reportNumber = 0;
        this.reporterName = "";
        this.waterLocation = "";
        this.waterType = WaterType.BOTTLED;
        this.waterCondition = WaterCondition.POTABLE;
    }

    /**
     * Getter for date and time.
     * @return the date and time of the water source report object
     */
    public String getDateAndTime() {

        return this.dateAndTime;
    }

    /**
     * Setter for date and time.
     * @param dt the new date and time of the water source report object
     */
    public void setDateAndTime(String dt) {

        dateAndTime = dt;
    }

    /**
     * Getter for date and time.
     * @return the date and time of the water source report object
     */
    public int getReportNumber() {

        return this.reportNumber;
    }

    /**
     * Setter for report number.
     * @param rNum the new report number of the water source report object
     */
    public void setReportNumber(int rNum) {

        reportNumber = rNum;
    }

    /**
     * Getter for reporter name.
     * @return the name of the water source report object
     */
    public String getReporterName() {

        return this.reporterName;
    }

    /**
     * Setter for reporter name.
     * @param rName the new reporter name of the water source report object
     */
    public void setReporterName(String rName) {

        reporterName = rName;
    }

    /**
     * Getter for water location.
     * @return the water location of the water source report object
     */
    public String getWaterLocation() {

        return this.waterLocation;
    }

    /**
     * Setter for water location.
     * @param wL the new water location of the water source report object
     */
    public void setWaterLocation(String wL) {

        waterLocation = wL;
    }

    /**
     * Getter for water type.
     * @return the water type of the water source report object
     */
    public WaterType getWaterType() {

        return this.waterType;
    }

    /**
     * Setter for water type.
     * @param wT the new water type of the water source report object
     */
    public void setWaterType(WaterType wT) {

        waterType = wT;
    }

    /**
     * Getter for water condition.
     * @return the water condition of the water source report object
     */
    public WaterCondition getWaterCondition() {

        return this.waterCondition;
    }

    /**
     * Setter for water condition.
     * @param wC the new water condition of the water source report object
     */
    public void setWaterConditionW(WaterCondition wC) {

        waterCondition = wC;
    }




}