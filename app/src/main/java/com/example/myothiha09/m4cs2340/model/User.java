package com.example.myothiha09.m4cs2340.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Team 27

/**
 * The POJO that represents a user. It implements the Parcelable to make it easier
 * to pass in intents.
 */
public class User implements Parcelable {
    // The list of users.
    public static List<UserType> userTypeList = Arrays.asList(UserType.values());
    public static ArrayList<User> usersList = new ArrayList<>();
    public static final String ARG_USER = "M5.GATECH.USER";
    // The attributes of a user.
    private String name;
    private String password;
    private String email;
    private UserType userType;

    /**
     * Constructor for the user
     * @param name the username of the user
     * @param password the password of the user
     * @param email the email of the user
     * @param userType the user type of the user (e.g. worker)
     */
    public User(String name, String password, String email, UserType userType) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    /**
     * A constructor for creating a user object using a Parcel.
     * @param in The parcel that would be used for creating a user object.
     */
    private User (Parcel in) {
        name = in.readString();
        password = in.readString();
        email = in.readString();
        userType = (UserType) in.readSerializable();
    }

    /**
     * Getter for the user type.
     * @return the user type
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Sets the user type
     * @param userType the user type to set the type of this user to.
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Gets the username of the user.
     * @return the username.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the username of the user.
     * @param name the username to set the user's username to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the password of the user.
     * @return the password to get.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user
     * @param password the password to set to
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's email.
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     * @param email The email to set to.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Describes the contents of the parcel class (not really implemented, but not really needed)
     * @return the contents.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Converts the user into a parcel object.
     */

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(password);
        parcel.writeString(email);
        parcel.writeSerializable(userType);
    }

    /**
     * Creates a User class. Required for the Parcelable User class.
     */
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        /**
         * Creates a user based on the parcel
         * @param in the parcel from which to create the user.
         * @return the created user
         */
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        /**
         * Creates an array of users
         * @param i The size of the array
         * @return the array of users
         */
        @Override
        public User[] newArray(int i) {
            return new User[i];
        }
    };
}
