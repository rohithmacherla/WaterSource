package com.example.myothiha09.m4cs2340.model;

import java.util.ArrayList;
// Team 27

/**
 * The POJO that represents a model that stores the data during a run of the app.
 */
public class Model {
    private final ArrayList<WaterSourceReport> waterSourceReports = new ArrayList<>();
    private final ArrayList<WaterPurityReport> waterPurityReports = new ArrayList<>();
    private int reportNumber = 0;
    private int purityReportNumber = 0;

    private static Model model;

    /**
     * Empty Constructor for Model
     */
    private Model() {

    }

    /**
     * Getter for an instance of the model.
     * @return the an instance of the model
     */
    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    /**
     * Method to add a new water report to the model instance.
     * @param w the new water report that's being added to the model instance
     */
    public void addWaterReport(WaterSourceReport w) {

        waterSourceReports.add(w);
    }


    /**
     * Method to add a new water report to the model instance.
     * @param w the new water report that's being added to the model instance
     */
    public void addWaterPurityReport(WaterPurityReport w) {

        waterPurityReports.add(w);
    }

    /**
     * Getter for the report number.
     * @return the an report number
     */
    public int getReportNumber() {
        if (getWaterSourceReports().size() != 0)
            reportNumber = getWaterSourceReports().size() - 1;

        return (++reportNumber);
    }

    /**
     * Getter for purity report number
     * @return the purity report number
     */

    public int getPurityReportNumber() {
        if (getWaterPurityReports().size() != 0)
            purityReportNumber = getWaterPurityReports().size() - 1;

        return (++purityReportNumber);
    }

    /**
     * Getter for the list of water reports added.
     * @return the list of water reports added
     */
    public ArrayList<WaterSourceReport> getWaterSourceReports() {
        return waterSourceReports;
    }

    /**
     * Getter for the list of water reports added.
     * @return the list of water reports added
     */
    public ArrayList<WaterPurityReport> getWaterPurityReports() {
        return waterPurityReports;
    }
}
