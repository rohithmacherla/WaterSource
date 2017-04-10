package com.example.myothiha09.m4cs2340.controller;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.myothiha09.m4cs2340.model.Model;
import com.example.myothiha09.m4cs2340.model.WaterPurityReport;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;



import com.example.myothiha09.m4cs2340.R;

import java.util.ArrayList;

import static com.example.myothiha09.m4cs2340.controller.MapsActivity.convertStringtoLatLng;


public class GraphActivity extends AppCompatActivity {


    private static final double MAX_RADIUS = 6.0; //Or whatever value you want
    private final Model model = Model.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle("Purity vs Time");
        String lat = getIntent().getStringExtra("SetUpInformationLat");
        String lng = getIntent().getStringExtra("SetUpInformationLong");
        double lati = Double.parseDouble(lat);
        double lngi = Double.parseDouble(lng);

        ArrayList<WaterPurityReport> toGraph = nearbyReports(lati, lngi, model.getWaterPurityReports());
        Log.d("TOGRAPHSIZE", toGraph.size()+"");
        DataPoint[] points = new DataPoint[toGraph.size()];
        DataPoint[] points2 = new DataPoint[toGraph.size()];
        int index = 0;
        for (WaterPurityReport report : toGraph) {
            points[index++] = new DataPoint(index + 1, report.getContaminantPPM());
        }
        index = 0;
        for (WaterPurityReport report : toGraph) {
            points2[index++] = new DataPoint(index + 1, report.getVirusPPM());
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
        series.setColor(Color.BLUE);
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(points2);
        series2.setColor(Color.RED);
        graph.addSeries(series);
        graph.addSeries(series2);
    }

    /**
     * Returns an ArrayList to be graphed.
     * @param lat latitude
     * @param lng longtitude
     * @param reports The list of water purity reports
     * @return the ArrayList
     */
    public static ArrayList<WaterPurityReport> nearbyReports(double lat, double lng, ArrayList<WaterPurityReport> reports) {
        ArrayList<WaterPurityReport> finalList = new ArrayList<>();
        if (reports == null) {
            return null;
        }
        if ((lat < -90 || lat > 90) || (lng > 180 || lng < -180)) {
            return null;
        }
        for (int i = 0; i < reports.size(); i++) {
            WaterPurityReport current = reports.get(i);
            if (distance(lat, lng, convertStringtoLatLng(current.getWaterLocation()).latitude,
                    convertStringtoLatLng(current.getWaterLocation()).longitude) < MAX_RADIUS) {
                finalList.add(current);
            }
        }
        return finalList;
    }

    /**
     * Calculate Distance
     * @param x1 coordinate 1
     * @param y1 coordinate 2
     * @param x2 coordinate 3
     * @param y2 coordinate 4
     * @return distance
     */
    private static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0));

    }
}
