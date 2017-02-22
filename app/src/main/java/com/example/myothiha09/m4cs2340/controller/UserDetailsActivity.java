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
        Button registerButton = (Button) findViewById(R.id.registerButton);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, User.userTypeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapter);
        if (getIntent().hasExtra(User.ARG_USER)) {
            newAccount = false;
            User user = getIntent().getParcelableExtra(User.ARG_USER);
            userName.setText(user.getName());
            userName.setEnabled(false);
            password.setText(user.getPassword());
            email.setText(user.getEmail());
            userType.setSelection(user.getUserType().getPosition());
            registerButton.setText("Save Changes");
        } else {
            newAccount = true;
        }



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!getIntent().hasExtra(User.ARG_USER) && checkUsername()) {
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
                    if (getIntent().hasExtra(User.ARG_USER)) {
                        for (User user : User.usersList) {
                            if (user.getName().equals(userName.getText().toString())) {
                                user.setPassword(password.getText().toString());
                                user.setEmail(email.getText().toString());
                                user.setUserType((UserType) userType.getSelectedItem());
                            }
                        }
                        finish();
                    }
                    else {
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
            }
        });


        Button registerCancel = (Button) findViewById(R.id.registerCancel);
        registerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
