package com.example.myothiha09.m4cs2340.controller;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.User;
import com.example.myothiha09.m4cs2340.model.UserType;

public class UserDetailsActivity extends AppCompatActivity {
    EditText userName;
    EditText password;
    EditText email;
    Spinner userType;
    boolean newAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userName = (EditText) findViewById(R.id.name_field);
        password = (EditText) findViewById(R.id.password_field);
        email = (EditText) findViewById(R.id.email_field);
        userType = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, User.userTypeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapter);
        if (getIntent().hasExtra(User.ARG_USER)) {
            newAccount = false;
            User user = getIntent().getParcelableExtra(User.ARG_USER);
            userName.setText(user.getName());
            password.setText(user.getPassword());
            email.setText(user.getEmail());
            userType.setSelection(user.getUserType().getPosition());
        } else {
            newAccount = true;
        }


        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkUsername()) {
                    Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_LONG).show();
                } else if (userName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input a username", Toast.LENGTH_LONG).show();
                } else if (password.getText().toString().length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password requirement not met", Toast.LENGTH_LONG).show();
                }  else if (email.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input an email", Toast.LENGTH_LONG).show();
                } else if (userType.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "Please select a user type", Toast.LENGTH_LONG).show();
                } else {
                    User user = new User(userName.getText().toString(),
                            password.getText().toString(),
                            email.getText().toString(),
                            (UserType) userType.getSelectedItem());
                    User.usersList.add(user);

                    Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                    intent.putExtra("Username", user.getName());
                    startActivity(intent);

                }
            }
        });


        Button registerCancel = (Button) findViewById(R.id.registerCancel);
        registerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
    private boolean checkUsername() {
        String username2 = userName.getText().toString();
        for(User user: User.usersList) {
            if (user.getName().equals(username2))
                return true;
        }
        return false;
    }
}
