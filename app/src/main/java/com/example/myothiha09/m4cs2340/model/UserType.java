package com.example.myothiha09.m4cs2340.model;

import java.io.Serializable;

// Team 27

/**
 * An enum that represents the user authorization level.
 * Each entry consists of the actual label, and the position in the spinner.
 */

public enum UserType implements Serializable {
    /**
     * First parameter is the string label for each user type,
     * second parameter is the position the user type has in the spinner.
     */
    USER("User", 0),
    WORKER("Worker", 1),
    MANAGER("Manager", 2),
    ADMIN("Admin", 3);

    String user;
    int position;

    /**
     * Constructor for the enum UserType
     * @param str the name of the user
     * @param position the number of the position
     */
    private UserType(String str, int position) {
        user = str;
        this.position = position;
    }

    /**
     * Getter a string representation of the user type object.
     * @return a string representation of the user type object.
     */
    @Override
    public String toString() {

        return user;
    }

    /**
     * Getter a string representation of the user type name.
     * @return a string representation of the user type name.
     */
    public String getUser() {

        return user;
    }

    /**
     * Getter a string representation of the user type's position in the spinner.
     * @return a string representation of the user type's position in the spinner.
     */
    public int getPosition() {

        return position;
    }
}
