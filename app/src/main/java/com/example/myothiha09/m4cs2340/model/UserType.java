package com.example.myothiha09.m4cs2340.model;

/**
 * Created by myothiha09 on 2/21/2017.
 */

public enum UserType {
    EMPTY(""),
    USER("User"),
    WORKER("Worker"),
    MANAGER("Manager"),
    ADMIN("Admin");

    String user;
    UserType(String str) {
        user = str;
    }

    @Override
    public String toString() {
        return user;
    }
}
