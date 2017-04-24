package com.example.myothiha09.m4cs2340.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

//Team: 27

/**
 * The activity that allows the user to enter in their credentials.
 * They see this activity right after clicking on the LOGIN button.
 *
 * The user has the option of logging in or by cancelling.
 */

public class LoginActivity extends AppCompatActivity {

    //References to the text views.
    private EditText username;
    private EditText password;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "LoginActivity";
    private Vibrator vibrator;


    /**
     * Assign the edit text views.
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle
     *                           contains the data it most recently supplied in
     *                           onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //initialize views
        username = (EditText) findViewById(R.id.userNameText);
        password = (EditText) findViewById(R.id.passwordText);
        progressDialog = new ProgressDialog(this);
        SharedPreferences mPrefs = getSharedPreferences("WaterCrowdSource", MODE_PRIVATE);
        Gson gson = new Gson();
        int index = 1;
        String json = mPrefs.getString("User"+index++, "");
        while (!json.equals("")) {
            User obj = gson.fromJson(json, User.class);
            User.usersList.add(obj);
            json = mPrefs.getString("User"+index++, "");
        }
        mAuth = FirebaseAuth.getInstance();
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
    }

    /**
     * When the login button is pressed, the login credentials
     * are first verified. If credentials are correct, the user
     * is redirected to their homescreen.
     * @param v The view that triggered this event.
     */
    public void onLoginPressed(View v) {
        if (checkPassword()) {
            //ProgressDialog
            progressDialog.setMessage("Loading! Please wait:)");
            progressDialog.show();

            String username2 = username.getText().toString();
            String password2 = password.getText().toString();

            mAuth.signInWithEmailAndPassword(username2, password2)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail:failed", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication Failed",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Log.w(TAG, "signInWithEmail:passed", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication Passed",
                                        Toast.LENGTH_LONG).show();
                            }

                            // ...
                        }
                    });



            Intent intent = new Intent(this, MainScreenActivity.class);
            intent.putExtra("Username", username.getText().toString());
            startActivity(intent);
        } else {
            AlertDialog dialog = createDialogBox();
            dialog.show();
        }
        vibrator.vibrate(25);
    }

    /**
     * Return to home screen if the cancel button is pressed.
     * @param v The view that triggered this event.
     */

    public void onCancelPressed(View v) {
        vibrator.vibrate(25);finish();
    }

    /**
     * Verifies the login credentials entered by the user.
     * @return Whether the login credentials are valid.
     */
    private boolean checkPassword() {
        String username2 = username.getText().toString();
        String password2 = password.getText().toString();
        for(User user: User.usersList) {
            if (user.getName().equals(username2)&&user.getPassword().equals(password2))
                return true;
        }
        return false;
    }

    /**
     * Displays an error if the login credentials are incorrect.
     * @return The error dialog.
     */
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
        vibrator.vibrate(100);


        return myDialogBuilder.create();
    }

    /**
     * Overrides the onStop function to return to home screen.
     */

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
