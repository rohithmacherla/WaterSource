package com.example.myothiha09.m4cs2340.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nidhi on 3/15/17.
 */

public class WaterPurityReport implements Parcelable {

    private String dateAndTime;
    private int reportNumber;
    private String reporterName;
    private String waterLocation;
    private WaterType waterType;
    private OverallCondition overallCondition;
    public static final String ARG_REPORT = "REPORT_TAGNAME";
    public static final String NEW_ARG_REPORT = "NEW REPORT_TAGNAME";
    public static List<WaterType> waterTypeList = Arrays.asList(WaterType.values());
    public static List<WaterCondition> overallConditionList = Arrays.asList(WaterCondition.values());
    private int virusPPM;
    private int contaminantPPM;



    /**
     * Constructor for WaterPurityReport
     * @param dt the date and time of the object's creation
     * @param rNum the report number
     * @param rName the name of the user
     * @param wL the water location put in
     * @param wT the water type selected
     * @param wC the water condition selected
     *
     */
    public WaterPurityReport(String dt, int rNum, String rName,
                             String wL, WaterType wT, OverallCondition wC, int vir, int cont) {
        this.dateAndTime = dt;
        this.reportNumber = rNum;
        this.reporterName = rName;
        this.waterLocation = wL;
        this.waterType = wT;
        this.overallCondition = wC;
        this.virusPPM = vir;
        this.contaminantPPM = cont;
    }

    /**
     * Empty Constructor for WaterPurityReport
     */
    public WaterPurityReport() {
        this.dateAndTime = "";
        this.reportNumber = 0;
        this.reporterName = "";
        this.waterLocation = new LatLng(0,0).toString();
        this.waterType = WaterType.BOTTLED;
        this.overallCondition = OverallCondition.SAFE;
        this.virusPPM = 0;
        this.contaminantPPM = 0;
    }

    private WaterPurityReport(Parcel in) {
        dateAndTime = in.readString();
        reportNumber = in.readInt();
        reporterName = in.readString();
        waterLocation = in.readString();
        waterType = (WaterType) in.readSerializable();
        overallCondition = (OverallCondition) in.readSerializable();
        virusPPM = in.readInt();
        contaminantPPM = in.readInt();
    }

    /**
     * Getter for date and time.
     * @return the date and time of the water purity report object
     */
    public String getDateAndTime() {

        return this.dateAndTime;
    }

    /**
     * Getter for virus PPM.
     * @return the virusppm of the water purity report object
     */
    public int getVirusPPM() {

        return this.virusPPM;
    }


    /**
     * Setter for virus ppm.
     * @param v the new virus ppm of the water purity report object
     */
    public void setVirusPPM(int v) {

        virusPPM = v;
    }


    /**
     * Setter for contaminant ppm.
     * @param c the contaminant of the water purity report object
     */
    public void setContaminantPPM(int c) {

        contaminantPPM = c;
    }

    /**
     * Getter for contaminant PPM.
     * @return the contaminantppm of the water purity report object
     */
    public int getContaminantPPM() {

        return this.contaminantPPM;
    }





    /**
     * Setter for date and time.
     * @param dt the new date and time of the water purity report object
     */
    public void setDateAndTime(String dt) {

        dateAndTime = dt;
    }

    /**
     * Getter for date and time.
     * @return the date and time of the water purity report object
     */
    public int getReportNumber() {

        return this.reportNumber;
    }

    /**
     * Setter for report number.
     * @param rNum the new report number of the water purity report object
     */
    public void setReportNumber(int rNum) {

        reportNumber = rNum;
    }

    /**
     * Getter for reporter name.
     * @return the name of the water purity report object
     */
    public String getReporterName() {

        return this.reporterName;
    }

    /**
     * Setter for reporter name.
     * @param rName the new reporter name of the water purity report object
     */
    public void setReporterName(String rName) {

        reporterName = rName;
    }

    /**
     * Getter for water location.
     * @return the water location of the water purity report object
     */
    public String getWaterLocation() {

        return this.waterLocation;
    }

    /**
     * Setter for water location.
     * @param wL the new water location of the water purity report object
     */
    public void setWaterLocation(String wL) {

        waterLocation = wL;
    }

    /**
     * Getter for water type.
     * @return the water type of the water purity report object
     */
    public WaterType getWaterType() {

        return this.waterType;
    }

    /**
     * Setter for water type.
     * @param wT the new water type of the water purity report object
     */
    public void setWaterType(WaterType wT) {

        waterType = wT;
    }

    /**
     * Getter for water condition.
     * @return the water condition of the water purity report object
     */
    public OverallCondition getOverallCondition() {

        return this.overallCondition;
    }

    /**
     * Setter for water condition.
     * @param oC the new water condition of the water purity report object
     */
    public void setOverallConditionW(OverallCondition oC) {

        overallCondition = oC;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(dateAndTime);
        parcel.writeInt(reportNumber);
        parcel.writeString(reporterName);
        parcel.writeString(waterLocation);
        parcel.writeSerializable(waterType);
        parcel.writeSerializable(overallCondition);

    }
    public static final Parcelable.Creator<WaterPurityReport> CREATOR = new Parcelable.Creator<WaterPurityReport>() {


        public WaterPurityReport createFromParcel(Parcel in) {
            return new WaterPurityReport(in);
        }

        /**
         * Creates an array of users
         * @param i The size of the array
         * @return the array of users
         */
        @Override
        public WaterPurityReport[] newArray(int i) {
            return new WaterPurityReport[i];
        }
    };
}
