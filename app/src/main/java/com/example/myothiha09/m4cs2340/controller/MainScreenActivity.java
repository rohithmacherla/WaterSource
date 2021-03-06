package com.example.myothiha09.m4cs2340.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.Model;
import com.example.myothiha09.m4cs2340.model.User;
import com.example.myothiha09.m4cs2340.model.UserType;
import com.example.myothiha09.m4cs2340.model.WaterPurityReport;
import com.example.myothiha09.m4cs2340.model.WaterSourceReport;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

// Team: 27

/**
 * This the homepage for each user. They see this after
 * signing in.
 */

public class MainScreenActivity extends AppCompatActivity {

    /**
     * References to the user and the text views of this activity
     */

    private static User user;
    private TextView userHeader;
    private TextView userTypeHeader;
    private Intent mapIntent;
    private Intent graphIntent;
    private SharedPreferences mPrefs;
    private ProgressDialog progressDialog;
    private Model model;
    private Vibrator vibrator;

    public static User getCurrentUser() {
        return user;
    }

    /**
     * Loads the user's data, sets the layout and view, set references
     * to the text views.
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle
     *                           contains the data it most recently supplied in
     *                           onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(this);
        mapIntent = new Intent(getApplicationContext(), MapsActivity.class);
        graphIntent = new Intent(getApplicationContext(), GraphSetupActivity.class);
        super.onCreate(savedInstanceState);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        setContentView(R.layout.activity_main_screen2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String username = getIntent().getStringExtra("Username");
        for (User user2: User.usersList) {
            if (user2.getName().equals(username)) {
                user = user2;
            }
        }
        mPrefs = getSharedPreferences("WaterCrowdSource", MODE_PRIVATE);
        model = Model.getInstance();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton pure= (FloatingActionButton) findViewById(R.id.purity);
        pure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(user.getUserType() == UserType.MANAGER) {
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                    //graphIntent.putExtra("User", user);
                    Log.wtf("came through", "work");
                    vibrator.vibrate(25);
                    startActivity(graphIntent);
                } else {
                    createDialogBox().show();

                }
                //ProgressDialog

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ProgressDialog
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                mapIntent.putExtra("User", user);
                vibrator.vibrate(25);
                startActivity(mapIntent);
            }
        });
        loadData();
        if (user.getUserType() == UserType.USER) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReportFragment()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Reports()).commit();
        }




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                vibrator.vibrate(25);
                // Handle navigation view item clicks here.
                int id = item.getItemId();
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                if (id == R.id.nav_home_screen) {
                    loadData();
                    if (user.getUserType() == UserType.USER) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReportFragment()).commit();
                    } else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Reports()).commit();
                    }
                    progressDialog.dismiss();
                } else if (id == R.id.nav_edit_screen) {
                    Intent intent = new Intent(getApplicationContext(), UserDetailsActivity.class);
                    intent.putExtra(User.ARG_USER, user);
                    startActivity(intent);
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        View layout = navigationView.getHeaderView(0);
        userHeader = (TextView) layout.findViewById(R.id.headerUsername);
        userTypeHeader = (TextView) layout.findViewById(R.id.headerUserType);
        userHeader.setText(user.getName());
        userTypeHeader.setText(user.getUserType().toString());


    }

    /**
     * Handles if the back button is pressed by modifying the drawer layout appropriately.
     */

    @Override
    public void onBackPressed() {
        progressDialog.dismiss();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    /**
     * Creates the dialog box for alert function. It also tells permission information.
     * @return nothing. Just prompts a dialog box.
     */
    private android.app.AlertDialog createDialogBox() {
        vibrator.vibrate(100);
        android.app.AlertDialog.Builder myDialogBuilder = new android.app.AlertDialog.Builder(this);
        myDialogBuilder.setTitle("Error!");
        myDialogBuilder.setMessage("You are not a Manager and therefore cannot access the " +
                "historical reports ");
        myDialogBuilder.setIcon(R.mipmap.ic_launcher);


        myDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        return myDialogBuilder.create();
    }

    /**
     * Inflates the options menu.
     * @param menu The menu to inflate
     * @return whether this was successful.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    /**
     * Handles when an item on the options item menu was selected.
     * @param item The item selected
     * @return Whether this method was successful.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Obtains the user data if this activity is restarted.
     */
    @Override
    protected void onRestart() {
        progressDialog.dismiss();
        super.onRestart();
        userHeader.setText(user.getName());
        userTypeHeader.setText(user.getUserType().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        final ArrayList<WaterPurityReport> waterPurityReports = model.getWaterPurityReports();
        final ArrayList<WaterSourceReport> waterSourceReports = model.getWaterSourceReports();
        waterPurityReports.clear();
        waterSourceReports.clear();
       /* SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        int index = 1;
        for (WaterSourceReport report: model.getWaterSourceReports()) {
            String json = gson.toJson(report);
            prefsEditor.putString("WaterSourceReport" + index++, json);
        }
        index = 1;
        for (WaterPurityReport report: model.getWaterPurityReports()) {
            String json = gson.toJson(report);
            prefsEditor.putString("WaterPurityReport" + index++, json);
        }
        index = 1;
        for (User user: User.usersList) {
            String json = gson.toJson(user);
            prefsEditor.putString("User" + index++, json);
        }
        prefsEditor.apply();*/
    }
    private void loadData() {
        Toast.makeText(getApplicationContext(), "Swipe down to Refresh", Toast.LENGTH_LONG).show();
        final ArrayList<WaterPurityReport> waterPurityReports = model.getWaterPurityReports();
        final ArrayList<WaterSourceReport> waterSourceReports = model.getWaterSourceReports();
        waterPurityReports.clear();
        waterSourceReports.clear();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        ValueEventListener waterSourceListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                for (DataSnapshot current: iterable) {
                    WaterSourceReport report = current.getValue(WaterSourceReport.class);
                    waterSourceReports.add(report);
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
                        waterPurityReports.add(report);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            reference.child("PurityReports").addValueEventListener(purityListener);
        }
        reference.child("SourceReports").addValueEventListener(waterSourceListener);
       /* int index = 1;
        Gson gson = new Gson();
        String json = mPrefs.getString("WaterSourceReport"+index++, "");
        while (!json.equals("")) {
            WaterSourceReport obj = gson.fromJson(json, WaterSourceReport.class);
            model.getWaterSourceReports().add(obj);
            json = mPrefs.getString("WaterSourceReport"+index++, "");
        }
        index = 1;
        json = mPrefs.getString("WaterPurityReport"+index++, "");
        while (!json.equals("")) {
            WaterPurityReport obj = gson.fromJson(json, WaterPurityReport.class);
            model.getWaterPurityReports().add(obj);
            json = mPrefs.getString("WaterPurityReport"+index++, "");
        }*/
        if (user.getUserType() == UserType.USER) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReportFragment()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Reports()).commit();
        }
    }

}
