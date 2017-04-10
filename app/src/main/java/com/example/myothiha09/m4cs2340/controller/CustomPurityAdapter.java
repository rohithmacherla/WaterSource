package com.example.myothiha09.m4cs2340.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.WaterPurityReport;

import java.util.ArrayList;

/**
 * Created by myothiha09 on 3/28/2017.
 */

class CustomPurityAdapter extends ArrayAdapter<WaterPurityReport> {
    private final Context context;
    private final ArrayList<WaterPurityReport> report;


    /**
     * A constructor for the ArrayAdapter
     * @param context the context from which this adapter was created
     * @param report The arraylist of reports to create this adapter.
     */
    public CustomPurityAdapter(Context context, ArrayList<WaterPurityReport> report) {
        super(context,-1,report);
        this.context = context;
        this.report = report;
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     * @param position The position of the item within the adapter's data set of the item whose
     *                 view we want.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to
     * @return The View that is being looked at
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.water_purity_view, parent, false);
        WaterPurityReport current = report.get(position);
        TextView dateLabel = (TextView) rowView.findViewById(R.id.date_time);
        TextView nameLabel = (TextView) rowView.findViewById(R.id.name);
        TextView reportNumber = (TextView) rowView.findViewById(R.id.numLabel);
        TextView location = (TextView) rowView.findViewById(R.id.waterLocation_field);
        TextView waterType = (TextView) rowView.findViewById(R.id.waterType_spinner);
        TextView waterCondition = (TextView) rowView.findViewById(R.id.overallCondition_spinner);
        TextView containment = (TextView) rowView.findViewById(R.id.contaminantPPM_field);
        TextView virus = (TextView) rowView.findViewById(R.id.virusPPM_field);

        dateLabel.setText(current.getDateAndTime());
        nameLabel.setText(current.getReporterName());
        reportNumber.setText(""+current.getReportNumber());
        location.setText(current.getWaterLocation());
        waterType.setText(current.getWaterType().toString());
        waterCondition.setText(current.getOverallCondition().toString());
        containment.setText(current.getContaminantPPM()+"");
        virus.setText(current.getVirusPPM()+"");


        return rowView;
    }


}
