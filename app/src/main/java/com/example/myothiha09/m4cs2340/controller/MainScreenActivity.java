package com.example.myothiha09.m4cs2340.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
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

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.Model;
import com.example.myothiha09.m4cs2340.model.User;
import com.example.myothiha09.m4cs2340.model.WaterCondition;
import com.example.myothiha09.m4cs2340.model.WaterSourceReport;
import com.example.myothiha09.m4cs2340.model.WaterType;

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
    private static TextView userHeader;
    private static TextView userTypeHeader;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String username = getIntent().getStringExtra("Username");
        for (User user2: User.usersList) {
            if (user2.getName().equals(username)) {
                user = user2;
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WaterReportActivity.class);
                startActivity(intent);
            }
        });
        Model model = Model.getInstance();
        WaterSourceReport report = new WaterSourceReport("Preadded", 0, "Preadded", "Sydney", WaterType.BOTTLED, WaterCondition.POTABLE);
        model.addWaterReport(report);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReportFragment()).commit();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_home_screen) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReportFragment()).commit();
                } else if (id == R.id.nav_edit_screen) {
                    Intent intent = new Intent(getApplicationContext(), UserDetailsActivity.class);
                    intent.putExtra(User.ARG_USER, user);
                    startActivity(intent);
                } else if (id == R.id.mapView) {
                    startActivity(new Intent(getApplicationContext(), MapsActivity.class));
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        super.onRestart();
        userHeader.setText(user.getName());
        userTypeHeader.setText(user.getUserType().toString());
    }
}
