package com.example.myothiha09.m4cs2340.controller;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myothiha09.m4cs2340.model.Model;
import com.example.myothiha09.m4cs2340.model.WaterPurityReport;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;



import com.example.myothiha09.m4cs2340.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import static com.example.myothiha09.m4cs2340.controller.MapsActivity.convertStringtoLatLng;


public class GraphActivity extends AppCompatActivity {


    private final double MAX_RADIUS = 6.0; //Or whatever value you want
    Model model = Model.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle("Purity vs Time");
        String lat = getIntent().getStringExtra("SetUpInformationLat");
        String lng = getIntent().getStringExtra("SetUpInformationLong");
        String year2 = getIntent().getStringExtra("SetUpInformationYear");
        double lati = Double.parseDouble(lat);
        double lngi = Double.parseDouble(lng);
        int year = Integer.parseInt(year2);

        ArrayList<WaterPurityReport> toGraph = nearbyReports(lati, lngi);
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
     * Returns a arraylist to be graphed.
     * @param lat latitude
     * @param lng longtitude
     * @return the arraylist
     */
    public ArrayList<WaterPurityReport> nearbyReports(double lat, double lng) {
        ArrayList<WaterPurityReport> reports = new ArrayList<>();
        for (WaterPurityReport current: model.getWaterPurityReports()) {
            if (distance(lat, lng, convertStringtoLatLng(current.getWaterLocation()).latitude,
                    convertStringtoLatLng(current.getWaterLocation()).longitude) < MAX_RADIUS) {
                reports.add(current);
            }
        }
        return reports;
    }

    /**
     * Calculate Distance
     * @param x1 coordinate 1
     * @param y1 coordinate 2
     * @param x2 coordinate 3
     * @param y2 cooridinate 4
     * @return distance
     */

    public double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0));

    }

}
