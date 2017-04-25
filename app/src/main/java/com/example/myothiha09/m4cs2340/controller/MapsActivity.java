package com.example.myothiha09.m4cs2340.controller;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myothiha09.m4cs2340.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.example.myothiha09.m4cs2340.model.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.myothiha09.m4cs2340.model.UserType.USER;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLongClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static GoogleMap mMap;
    private Model model;
    private User user;
    private ProgressDialog progressDialog;
    private Vibrator vibrator;
    private GoogleApiClient mGoogleApiClient;
    private String mLatitudeText;
    private String mLongitudeText;
    ArrayList<WaterPurityReport> waterPurityReports;
    ArrayList<WaterSourceReport> waterSourceReports;

    @Override
    /*
      Do the initials setup necessary for a map activity and show instruction to users through alert..
      @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle
     *                           contains the data it most recently supplied in
     *                           onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        user = getIntent().getParcelableExtra("User");
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
        waterSourceReports = model.getWaterSourceReports();
        waterPurityReports = model.getWaterPurityReports();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        final Button locationButton = (Button) findViewById(R.id.location);

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
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);
          /*
        for (WaterSourceReport report: model.getWaterSourceReports()) {
            String latLongLocation = report.getWaterLocation();
            LatLng location = convertStringtoLatLng(latLongLocation);
            mMap.addMarker(new MarkerOptions().position(location));
        }
        for (WaterPurityReport current : model.getWaterPurityReports()) {
            String latLongLocation = current.getWaterLocation();
            LatLng location = convertStringtoLatLng(latLongLocation);
            mMap.addMarker(new MarkerOptions().position(location));
        }*/

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        ValueEventListener waterSourceListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                for (DataSnapshot current : iterable) {
                    //Log.d("SomeRandomInfo", current.getValue(String.class));
                    WaterSourceReport report = current.getValue(WaterSourceReport.class);
                    //reports.add(report);
                    String latLongLocation = report.getWaterLocation();
                    LatLng location = convertStringtoLatLng(latLongLocation);
                    mMap.addMarker(new MarkerOptions().position(location));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        if (user.getUserType() != UserType.USER) {

            ValueEventListener purityListener = new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                    for (DataSnapshot current : iterable) {
                        WaterPurityReport report = current.getValue(WaterPurityReport.class);
                        //reports.add(report);
                        String latLongLocation = report.getWaterLocation();
                        LatLng location = convertStringtoLatLng(latLongLocation);
                        mMap.addMarker(new MarkerOptions().position(location));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            reference.child("PurityReports").addValueEventListener(purityListener);
        }
        reference.child("SourceReports").addValueEventListener(waterSourceListener);

    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    /**
     * The listener method to decide action if a user click a marker
     * @param marker the marker that is clicked
     * @return whether an action is executed or not
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        vibrator.vibrate(25);
        ArrayList<WaterSourceReport> arrayList = model.getWaterSourceReports();
        ArrayList<WaterPurityReport> arrayList2 = model.getWaterPurityReports();
        if (user.getUserType() == USER) {
            for (WaterSourceReport current : arrayList) {
                if (current.getWaterLocation().equals(marker.getPosition().toString())) {
                    Intent intent = new Intent(this, WaterReportActivity.class);
                    intent.putExtra(WaterSourceReport.ARG_REPORT, current);
                    startActivity(intent);
                    return true;
                }
            }
        } else {
            Log.d("ArrayListSize", arrayList.size() + "");
            for (WaterSourceReport current : arrayList) {
                if (current.getWaterLocation().equals(marker.getPosition().toString())) {
                    Intent intent = new Intent(getApplicationContext(), WaterReportActivity.class);
                    intent.putExtra(WaterSourceReport.ARG_REPORT, current);
                    startActivity(intent);
                    return true;
                }
            }
            for (WaterPurityReport current : arrayList2) {
                if (current.getWaterLocation().equals(marker.getPosition().toString())) {
                    Intent intent = new Intent(getApplicationContext(), WaterPurityReportActivity.class);
                    intent.putExtra(WaterPurityReport.ARG_REPORT, current);
                    startActivity(intent);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Start the ability to add a water report at the location user long pressed at.
     * @param latLng the location of where user long press.
     */
    @Override
    public void onMapLongClick(final LatLng latLng) {
        vibrator.vibrate(50);
        if (user.getUserType() == USER) {
            startWaterSourceReport(latLng);
        } else {
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.map_report_alert_dialog);
            dialog.setTitle("Type of Report");
            Button sourceButton = (Button) dialog.findViewById(R.id.sourceButton);
            Button purityButton = (Button) dialog.findViewById(R.id.purityButton);
            Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);

            sourceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startWaterSourceReport(latLng);
                    dialog.dismiss();
                }
            });

            purityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startWaterPurityReport(latLng);
                    dialog.dismiss();
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();

            //AlertDialog.Builder builder = new AlertDialog.Builder(this);

            /*
            builder.setMessage("Would you like to submit a Water Source Report or a " +
                    "Water Purity Report?");
                    */
            /*
            builder.setItems(new CharSequence[]
                            {"Source", "Purity"},
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // The 'which' argument contains the index position
                            // of the selected item
                            switch (which) {
                                case 0:
                                    startWaterSourceReport(latLng);
                                case 1:
                                    startWaterPurityReport(latLng);
                            }
                        }
                    });*/
            /*
            builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("Source", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startWaterSourceReport(latLng);
                }
            })
                    .setPositiveButton("Purity", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startWaterPurityReport(latLng);
                }
            });

            builder.create().show();
            */


        }

    }

    /**
     * Start water purity report activity at the clicked location.
     * @param latLng clicked location
     */
    private void startWaterPurityReport(LatLng latLng) {
        vibrator.vibrate(25);
        latLng = convertStringtoLatLng(latLng.toString());

        Intent intent = new Intent(this, WaterPurityReportActivity.class);
        intent.putExtra(WaterPurityReport.NEW_ARG_REPORT, latLng.toString());
        startActivity(intent);
    }

    /**
     * Start water source report activity at clicked location.
     * @param latLng clicked location.
     */

    private void startWaterSourceReport(LatLng latLng) {
        vibrator.vibrate(25);
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
     * Format the latLng to be more readable
     * @param latLongLocation LatLng passed in as string
     * @return the formatted LatLng
     * @throws IllegalArgumentException if latLongLocation cannot be
     * converted to LatLng object.
     */
    public static LatLng convertStringtoLatLng(String latLongLocation) {
        int index = latLongLocation.indexOf(",");
        int index2 = latLongLocation.indexOf("(");
        if (index == -1 || index2 == -1) {
            throw new IllegalArgumentException("String input must contain a comma and "
                    + "set of parenthesis");
        }
        String lat = latLongLocation.substring(index2 + 1, index).trim();
        String lng = latLongLocation.substring(index + 1, latLongLocation.length() - 1).trim();
        try {
            double latitude = Double.parseDouble(lat);
            double longitude = Double.parseDouble(lng);
            DecimalFormat format = new DecimalFormat("###.00");
            latitude = Double.parseDouble(format.format(latitude));
            longitude = Double.parseDouble(format.format(longitude));
            if (latitude < -90.0 || latitude > 90.0 ||
                    longitude < -180.0 || longitude >= 180.0) {
                throw new IllegalArgumentException("Longitude must be" +
                        "between -180.0 and 180.0 and latitude " +
                        "must be between -90.0 and 90.0");
            }
            return new LatLng(latitude, longitude);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("String input does not consist of two" +
                    "doubles that represent latitude and longitude");
        }
    }

    /**
     * What to do on backPressed
     * @param v the view back is pressed at
     */
    public void onGoBackPressed(View v) {
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        vibrator.vibrate(25);
        this.onBackPressed();
    }

    public void useCurrentLocation(View v) {
        // progressDialog.show();
        // progressDialog.setMessage("Loading...");
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if( !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error!");  // GPS not found
            builder.setMessage("Location Disabled"); // Want to enable?
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            builder.setNegativeButton("No", null);
            builder.create().show();
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            Toast.makeText(getApplicationContext(), "Location Permission denied", Toast.LENGTH_LONG).show();
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        String tempLat = mLatitudeText;
        String tempLong = mLongitudeText;
        if (mLastLocation != null) {
            mLatitudeText = (String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText = (String.valueOf(mLastLocation.getLongitude()));
        }
        vibrator.vibrate(25);
        if (!(mLongitudeText.equals(tempLong) && mLatitudeText.equals(tempLat))) {
            onMapLongClick(convertStringtoLatLng("(" + mLatitudeText + ", " + mLongitudeText + ")"));
        } else {
            ArrayList<WaterSourceReport> arrayList = model.getWaterSourceReports();
            Toast.makeText(getApplicationContext(), "Report already at location", Toast.LENGTH_LONG).show();
            for (WaterSourceReport current : arrayList) {
                if (current.getWaterLocation().equals(convertStringtoLatLng("(" + mLatitudeText + ", " + mLongitudeText + ")").toString())) {
                    Intent intent = new Intent(this, WaterReportActivity.class);
                    intent.putExtra(WaterSourceReport.ARG_REPORT, current);
                    startActivity(intent);
                    return;
                }
            }

        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText = (String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText = (String.valueOf(mLastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
