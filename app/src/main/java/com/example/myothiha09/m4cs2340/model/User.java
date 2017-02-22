package com.example.myothiha09.m4cs2340.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by myothiha09 on 2/21/2017.
 */

public class User {
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
}
