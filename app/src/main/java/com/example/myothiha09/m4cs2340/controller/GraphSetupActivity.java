package com.example.myothiha09.m4cs2340.controller;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myothiha09.m4cs2340.R;


/**
 * Team 27.
 *
 * This is the place where a user can add the details of
 *  a new water report.
 */

public class GraphSetupActivity extends AppCompatActivity {



    private EditText year;
    private EditText longtitude;
    private EditText latitude;

    private Intent graphIntent;
    private Vibrator vibrator;


    /**
     * Method that initializes the water report activity. Provides all the UI elements.
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     *                           this is the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        year = (EditText) findViewById(R.id.year_field);
        year.setText("2017");
        year.setEnabled(false);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        longtitude = (EditText) findViewById(R.id.longitude_field);
        latitude = (EditText) findViewById(R.id.latitude_field);
        graphIntent = new Intent(getApplicationContext(), GraphActivity.class);
        //Add button
        final Button graphButton = (Button) findViewById(R.id.graphButton);
        graphButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                vibrator.vibrate(25);
                final String yearStr = year.getText().toString();
                final String longStr = longtitude.getText().toString();
                final String latitStr = latitude.getText().toString();
                graphIntent.putExtra("SetUpInformationLong", longStr);
                graphIntent.putExtra("SetUpInformationLat", latitStr+"");
                graphIntent.putExtra("SetUpInformationYear", yearStr+"");
                startActivity(graphIntent);
                GraphSetupActivity.super.onBackPressed();
            }


        });
    }
}
