package com.example.myothiha09.m4cs2340.controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.Model;
import com.example.myothiha09.m4cs2340.model.WaterCondition;
import com.example.myothiha09.m4cs2340.model.WaterSourceReport;
import com.example.myothiha09.m4cs2340.model.WaterType;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapViewFragment extends Fragment implements GoogleMap.OnMarkerClickListener{

    MapView mMapView;
    Model model;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_fragment, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        model = Model.getInstance();
        mMapView.onCreate(savedInstanceState);


        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                // googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(-34, 151);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Sydney"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        ArrayList<WaterSourceReport> arrayList = model.getWaterSourceReports();
        for (WaterSourceReport current : arrayList) {
            if(current.getWaterLocation().equals(marker.getTitle())) {
                final Dialog dialog = new Dialog(getActivity());
                TextView dateLabel = (TextView) dialog.findViewById(R.id.date_timeLabel);
                TextView nameLabel = (TextView) dialog.findViewById(R.id.nameLabel);
                TextView reportNumber = (TextView) dialog.findViewById(R.id.numLabel);
                TextView location = (TextView) dialog.findViewById(R.id.waterLocationLabel);
                TextView waterType = (TextView) dialog.findViewById(R.id.waterTypeLabel);
                TextView waterCondition = (TextView) dialog.findViewById(R.id.waterConditionLabel);
                dialog.setContentView(R.layout.water_report_view);
                dialog.setTitle("Water Report Title");
                dateLabel.setText(current.getDateAndTime());
                nameLabel.setText(current.getReporterName());
                reportNumber.setText(""+current.getReportNumber());
                location.setText(current.getWaterLocation());
                waterType.setText(current.getWaterType().toString());
                waterCondition.setText(current.getWaterCondition().toString());
            }
        }
        return false;
    }
}
