package com.example.myothiha09.m4cs2340.model;

import java.io.Serializable;

/**
 * Created by myothiha09 on 2/21/2017.
 */

public enum UserType implements Serializable {
    EMPTY("", 0),
    USER("User", 1),
    WORKER("Worker", 2),
    MANAGER("Manager", 3),
    ADMIN("Admin", 4);

    String user;
    int position;
    private UserType(String str, int position) {
        user = str;
        this.position = position;
    }

    @Override
    public String toString() {
        return user;
    }

    public String getUser() {
        return user;
    }

    public int getPosition() {
        return position;
    }
}
