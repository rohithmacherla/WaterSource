package com.example.myothiha09.m4cs2340.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.Model;
import com.example.myothiha09.m4cs2340.model.WaterSourceReport;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLongClickListener {

    private static GoogleMap mMap;
    private Model model;
    @Override
    /**
     * Do the initials setup necessary for a map activity and show instruction to users throguh alert..
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle
     *                           contains the data it most recently supplied in
     *                           onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        AlertDialog alertDialog = new AlertDialog.Builder(MapsActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Long click to add a new location. Click a pin to edit/view details");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
        model = Model.getInstance();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);
        for (WaterSourceReport report: model.getWaterSourceReports()) {
            String latLongLocation = report.getWaterLocation();
            LatLng location = convertStringtoLatLng(latLongLocation);
            mMap.addMarker(new MarkerOptions().position(location));
        }


    }

    /**
     * The listener method to decide action if a user click a marker
     * @param marker the marker that is clicked
     * @return whether an action is executed or not
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        ArrayList<WaterSourceReport> arrayList = model.getWaterSourceReports();
        for (WaterSourceReport current : arrayList) {
            if(current.getWaterLocation().equals(marker.getPosition().toString())) {
                Intent intent = new Intent(this, WaterReportActivity.class);
                intent.putExtra(WaterSourceReport.ARG_REPORT, current);
                startActivity(intent);
                return true;
            }
        }
        return false;
    }

    /**
     * Start the ability to add a water report at the location user long pressed at.
     * @param latLng the location of where user long press.
     */
    @Override
    public void onMapLongClick(LatLng latLng) {
        latLng = convertStringtoLatLng(latLng.toString());

        Intent intent = new Intent(this, WaterReportActivity.class);
        intent.putExtra(WaterSourceReport.NEW_ARG_REPORT, latLng.toString());
        startActivity(intent);
    }

    /**
     * A static method to add marker from WaterReportActivity
     * @param latLng the location of the the marker
     */
    public static void addMarker(LatLng latLng) {
        mMap.addMarker(new MarkerOptions().position(latLng));
    }

    /**
     * Format the latLng to be more readble
     * @param str LatLng passed in as string
     * @return the formatted LatLng
     */
    public static LatLng convertStringtoLatLng(String str) {
        String latLongLocation = str;
        int index = latLongLocation.indexOf(",");
        int index2 = latLongLocation.indexOf("(");
        String lat = latLongLocation.substring(index2+1, index).trim();
        String lng = latLongLocation.substring(index+1, latLongLocation.length()-1).trim();
        double latitude = Double.parseDouble(lat);
        double longitude = Double.parseDouble(lng);
        DecimalFormat format = new DecimalFormat("###.00");
        latitude = Double.parseDouble(format.format(latitude));
        longitude = Double.parseDouble(format.format(longitude));
        return new LatLng(latitude, longitude);
    }

    /**
     * What to do on backPressed
     * @param v the view back is pressed at
     */
    public void onGoBackPressed(View v) {
        this.onBackPressed();
    }


}
