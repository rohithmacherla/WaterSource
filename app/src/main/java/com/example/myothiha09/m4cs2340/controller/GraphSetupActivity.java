package com.example.myothiha09.m4cs2340.controller;


import android.content.Intent;
import android.os.Bundle;
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



    private static EditText year;
    private static EditText longtitude;
    private static EditText latitude;

    private Intent graphIntent;


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
        final String yearStr = year.toString();
        longtitude = (EditText) findViewById(R.id.longitude_field);
        final String longStr = longtitude.toString();
        latitude = (EditText) findViewById(R.id.latitude_field);
        final String latitStr = latitude.toString();




        graphIntent = new Intent(getApplicationContext(), GraphActivity.class);





        //Add button
        final Button graphButton = (Button) findViewById(R.id.graphButton);

        graphButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                graphIntent.putExtra("SetUpInformation", longStr+" "+latitStr+" "+ yearStr);
                startActivity(graphIntent);


                GraphSetupActivity.super.onBackPressed();
            }


        });




    }

}
