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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import android.support.v7.app.AppCompatActivity;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.widget.Button;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.UserType;
import com.example.myothiha09.m4cs2340.model.WaterCondition;
import com.example.myothiha09.m4cs2340.model.WaterSourceReport;
import com.example.myothiha09.m4cs2340.model.User;
import com.example.myothiha09.m4cs2340.model.Model;
import com.example.myothiha09.m4cs2340.model.WaterType;


/**
 * Team 27.
 *
 * This is the place where a user can add the details of
 *  a new water report.
 */

public class WaterReportActivity extends AppCompatActivity {
    private static WaterSourceReport waterSourceReport;
    private static Model model;

    private static String dateAndTime;

    private static int reportNumber;

    private static String reporterName;

    private static EditText location;
    private static String chosenLocation;

    private static Spinner waterTypes;

    private static Spinner waterConditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waterreport);

        //Autogenerating Date and Time
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        dateAndTime = format.format(rightNow.getTime());

        TextView dateTime = (TextView) findViewById(R.id.date_time);
        dateTime.setText(dateAndTime);

        //Autogenerating report number
        reportNumber = model.getReportNumber();

        //Autogenerating User Name
        User currentUser = MainScreenActivity.getCurrentUser();
        reporterName = currentUser.getName();


        TextView user = (TextView) findViewById(R.id.name);
        user.setText(reporterName);

        //Setting the Location of the Water
        location = (EditText) findViewById(R.id.waterLocation_field);
        chosenLocation = location.getText().toString();

        //Spinner for Water Type
        waterTypes = (Spinner) findViewById(R.id.waterType_spinner);

        //Spinner for Water Condition
        waterConditions = (Spinner) findViewById(R.id.waterType_spinner);

        //Cancel button
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WaterReportActivity.super.onBackPressed();
            }
        });

        //Add button
        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                waterSourceReport = new WaterSourceReport();
                waterSourceReport.setDateAndTime(dateAndTime);
                waterSourceReport.setReportNumber(reportNumber);
                waterSourceReport.setReporterName(reporterName);
                waterSourceReport.setWaterLocation(chosenLocation);

                //watertype
                waterSourceReport.setWaterType((WaterType) waterTypes.getSelectedItem());

                //watercondition
                waterSourceReport.setWaterConditionW(
                        (WaterCondition) waterConditions.getSelectedItem());

                model.addWaterReport(waterSourceReport);

                WaterReportActivity.super.onBackPressed();
            }


        });




    }
}
