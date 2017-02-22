package com.example.myothiha09.m4cs2340.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by myothiha09 on 2/21/2017.
 */

public class User implements Parcelable {
    public static List<UserType> userTypeList = Arrays.asList(UserType.values());
    public static ArrayList<User> usersList = new ArrayList<User>();
    private String name;
    private String password;
    private String email;
    private UserType userType;

    public User(String name, String password, String email, UserType userType) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    private User (Parcel in) {
        name = in.readString();
        password = in.readString();
        email = in.readString();
        userType = (UserType) in.readSerializable();
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(password);
        parcel.writeString(email);
        parcel.writeSerializable(userType);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel in) {
            return null;
        }

        @Override
        public User[] newArray(int i) {
            return new User[i];
        }
    };
}
