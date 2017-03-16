package com.example.myothiha09.m4cs2340.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import android.support.v7.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.widget.Button;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.UserType;
import com.example.myothiha09.m4cs2340.model.OverallCondition;
import com.example.myothiha09.m4cs2340.model.WaterPurityReport;
import com.example.myothiha09.m4cs2340.model.User;
import com.example.myothiha09.m4cs2340.model.Model;
import com.example.myothiha09.m4cs2340.model.WaterSourceReport;
import com.example.myothiha09.m4cs2340.model.WaterType;
import com.google.android.gms.maps.model.LatLng;


/**
 * Team 27.
 *
 * This is the place where a user can add the details of
 *  a new water report.
 */

public class WaterPurityReportActivity extends AppCompatActivity {
    private static WaterPurityReport waterPurityReport;

    private static Model model;


    private static String dateAndTime;

    private static int reportNumber;

    private static String reporterName;

    private static EditText location;
    private static String chosenLocation;

    private static Spinner waterTypes;

    private static Spinner overallConditions;
    private WaterPurityReport report;

    /**
     * Method that initializes the water report activity. Provides all the UI elements.
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     *                           this is the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_purity_report);

        //Autogenerating Date and Time
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        dateAndTime = format.format(rightNow.getTime());

        model = Model.getInstance();

        TextView dateTime = (TextView) findViewById(R.id.date_time);
        dateTime.setText(dateAndTime);




        //Autogenerating report number


        //Autogenerating User Name
        User currentUser = MainScreenActivity.getCurrentUser();
        reporterName = currentUser.getName();


        TextView user = (TextView) findViewById(R.id.name);
        user.setText(reporterName);




        //Spinner for Water Type
        waterTypes = (Spinner) findViewById(R.id.waterType_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, WaterPurityReport.waterTypeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterTypes.setAdapter(adapter);

        //Spinner for Water Condition
        overallConditions = (Spinner) findViewById(R.id.overallCondition_spinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, WaterPurityReport.overallConditionList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        overallConditions.setAdapter(adapter2);

        if(getIntent().hasExtra(WaterPurityReport.NEW_ARG_REPORT)) {
            chosenLocation = getIntent().getStringExtra(WaterPurityReport.NEW_ARG_REPORT);
        } else if(getIntent().hasExtra(WaterPurityReport.ARG_REPORT)) {
            report = getIntent().getParcelableExtra(WaterPurityReport.ARG_REPORT);
            chosenLocation = report.getWaterLocation();
            dateTime.setText(report.getDateAndTime());
            user.setText(report.getReporterName());
            waterTypes.setSelection(report.getWaterType().getPosition());
            overallConditions.setSelection(report.getOverallCondition().getPosition());
            location.setText(chosenLocation);
        }
        //Setting the Location of the Water
        location = (EditText) findViewById(R.id.waterLocation_field);
        location.setText(chosenLocation);
        location.setEnabled(false);

        //Cancel button
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WaterPurityReportActivity.super.onBackPressed();
                //implement deleting marker
            }
        });

        //Add button
        final Button addButton = (Button) findViewById(R.id.addButton);
        if (report == null) {
            waterPurityReport = new WaterPurityReport();
        }
        else {
            waterPurityReport = model.getWaterPurityReports().get(report.getReportNumber()-1);
            addButton.setText("Save");
        }
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                waterPurityReport.setWaterLocation(chosenLocation.toString());
                waterPurityReport.setDateAndTime(dateAndTime);

                waterPurityReport.setReporterName(reporterName);

                //watertype
                waterPurityReport.setWaterType((WaterType) waterTypes.getSelectedItem());

                //watercondition
                waterPurityReport.setOverallConditionW(
                        (OverallCondition) overallConditions.getSelectedItem());

                if (report == null) {
                    model.addWaterPurityReport(waterPurityReport);
                    reportNumber = model.getReportNumber();
                    waterPurityReport.setReportNumber(reportNumber);
                    MapsActivity.addMarker(MapsActivity.convertStringtoLatLng(chosenLocation));
                }

                WaterPurityReportActivity.super.onBackPressed();
            }


        });




    }
}
