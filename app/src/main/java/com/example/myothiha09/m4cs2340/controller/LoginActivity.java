package com.example.myothiha09.m4cs2340.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myothiha09.m4cs2340.R;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        username = (EditText) findViewById(R.id.userNameText);
        password = (EditText) findViewById(R.id.passwordText);
    }
    public void onLoginPressed(View v) {
        if (checkPassword()) {
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        } else {
            AlertDialog dialog = createDialogBox();
            dialog.show();
        }
    }
    public void onCancelPressed(View v) {
        finish();
    }
    private boolean checkPassword() {
        String username2 = username.getText().toString();
        String password2 = password.getText().toString();
        if (username2.equals("user") && password2.equals("pass")) {
            return true;
        }
        return false;
    }
    private android.app.AlertDialog createDialogBox() {
        android.app.AlertDialog.Builder myDialogBuilder = new android.app.AlertDialog.Builder(this);
        myDialogBuilder.setTitle("Error!");
        myDialogBuilder.setMessage("Wrong password or username.");
        myDialogBuilder.setIcon(R.mipmap.ic_launcher);


        myDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        return myDialogBuilder.create();
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
