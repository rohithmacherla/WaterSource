package com.example.myothiha09.m4cs2340.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.WaterSourceReport;

import java.util.ArrayList;

/**
 * Created by vgiridhar on 3/1/17.
 */

class CustomArrayAdapter extends ArrayAdapter<WaterSourceReport> {
    private final Context context;
    private final ArrayList<WaterSourceReport> report;
    private View rowView = null;


    /**
     * A constructor for the ArrayAdapter
     * @param context the context from which this adapter was created
     * @param report The ArrayList of reports to create this adapter.
     */
    public CustomArrayAdapter(Context context, ArrayList<WaterSourceReport> report) {
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
     * @return The view that is being looked at
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (rowView == null)
        rowView = inflater.inflate(R.layout.water_report_view, parent, false);
        TextView dateLabel = (TextView) rowView.findViewById(R.id.date_timeLabel);
        TextView nameLabel = (TextView) rowView.findViewById(R.id.nameLabel);
        TextView reportNumber = (TextView) rowView.findViewById(R.id.numLabel);
        TextView location = (TextView) rowView.findViewById(R.id.waterLocationLabel);
        TextView waterType = (TextView) rowView.findViewById(R.id.waterTypeLabel);
        TextView waterCondition = (TextView) rowView.findViewById(R.id.waterConditionLabel);

            WaterSourceReport current = report.get(position);
            dateLabel.setText(current.getDateAndTime());
            nameLabel.setText(current.getReporterName());
            reportNumber.setText(""+current.getReportNumber());
            location.setText(current.getWaterLocation());
            waterType.setText(current.getWaterType().toString());
            waterCondition.setText(current.getWaterCondition().toString());

        return rowView;
    }

    
    
}
