package com.example.myothiha09.m4cs2340.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
import android.util.Log;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.User;
import com.example.myothiha09.m4cs2340.model.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import  java.util.ArrayList;

// Team 27

/**
 * This activity allows a user to edit their account or create a new account.
 * This activity is called when the user presses "Registration" or "Edit Profile"
 */
public class UserDetailsActivity extends AppCompatActivity {

    /**
     * References to the views.
     */
    private EditText userName;
    private EditText password;
    private EditText email;
    private Spinner userType;
    private Vibrator vibrator;

    private static final String TAG = "UserDetailsActivity";

    //progress bar for fire base registering and firebaseAuth
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    /**
     * Sets references to all the views, creates the adapter,
     * sets the views to the user details the user is just editing their profile,
     * and adds event handlers to the buttons.
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle
     *                           contains the data it most recently supplied in
     *                           onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //instantiate views from activity_registration.xml
        userName = (EditText) findViewById(R.id.name_field);
        password = (EditText) findViewById(R.id.password_field);
        email = (EditText) findViewById(R.id.email_field);
        userType = (Spinner) findViewById(R.id.spinner);
        Button registerButton = (Button) findViewById(R.id.registerButton);

        //instantiate the progressBar
        progressDialog = new ProgressDialog(this);

        //instantiate the firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };





        //create the spinner for UserType
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, User.userTypeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapter);

        //Sets the views to the current details of the user if the user is just
        //editing their profile.
        if (getIntent().hasExtra(User.ARG_USER)) {
            User user = getIntent().getParcelableExtra(User.ARG_USER);
            userName.setText(user.getName());
            userName.setEnabled(false);
            password.setText(user.getPassword());
            email.setText(user.getEmail());
            userType.setSelection(user.getUserType().getPosition());
            registerButton.setText("Save Changes");
        }

        //Creates the user if the register button is pressed.
        //But first validates all the input the user has entered.

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!getIntent().hasExtra(User.ARG_USER) && checkUsername(userName.getText().toString(), User.usersList)) {
                    Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_LONG).show();
                } else if (userName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input a username", Toast.LENGTH_LONG).show();
                } else if (password.getText().toString().length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password requirement not met", Toast.LENGTH_LONG).show();
                }  else if (email.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input an email", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog.setMessage("Registering user! Please wait :)");
                    progressDialog.show();
                    vibrator.vibrate(25);


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
                        progressDialog.setMessage("Registering user! Please wait :)");
                        progressDialog.show();

                        FirebaseAuth mAuth = firebaseAuth;
                        //firebase
                        mAuth.createUserWithEmailAndPassword(userName.getText().toString(), password.getText().toString())
                                .addOnCompleteListener(UserDetailsActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                        // If sign in fails, display a message to the user. If sign in succeeds
                                        // the auth state listener will be notified and logic to handle the
                                        // signed in user can be handled in the listener.
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(UserDetailsActivity.this, "Authentication Failed",
                                                    Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(UserDetailsActivity.this, "Authentication Passed",
                                                    Toast.LENGTH_LONG).show();
                                        }

                                        // ...
                                    }
                                });


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


        //Event handler for when the user clicks the cancel button.
        Button registerCancel = (Button) findViewById(R.id.registerCancel);
        registerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(25);
                finish();
            }
        });
    }

    /**
     * Checks to see if the username is not taken.
     * @param username the username to be checked
     * @param list the list of users stored
     * @return if the username was taken or not (true if taken).
     */
    public static boolean checkUsername(String username, ArrayList<User> list) {
        if (username == null || list == null ) {
            return false;
        }

        for(int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles if this activity is stopped.
     */
    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
        finish();
    }

    //experimental
    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

}
