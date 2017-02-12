package com.example.myothiha09.m4cs2340;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
    }
    public void onClick(View v) {
        if (v.getId() == R.id.loginButton) {
            Intent intent = new Intent(this, LoginAcitivity.class);
            startActivity(intent);
        }
    }
}
