package com.example.myothiha09.m4cs2340.model;

import java.io.Serializable;

// Team 27

/**
 * An enum that represents the type of a water location.
 * Each entry consists of the actual label, and the position in the spinner.
 */
public enum WaterType implements Serializable {
    /**
     * First parameter is the string label for each water type,
     * second parameter is the position the water type has in the spinner.
     */
    BOTTLED("Bottled", 0),
    WELL("Well", 1),
    STREAM("Stream", 2),
    LAKE("Lake", 3),
    SPRING("Spring", 4),
    OTHER("Other", 5);


    private String name;
    private int position;

    /**
     * Constructor for the enum WaterType
     * @param n the name of the user
     * @param p the position
     */
    private WaterType(String n, int p) {
        name = n;
        position = p;
    }

    /**
     * Return a string representation of the water type object.
     * @return a string representation of the water type object.
     */
    public String toString() {

        return name;
    }

    /**
     * Getter a string representation of the water type name.
     * @return a string representation of the water type name.
     */
    public String getName() {

        return name;
    }

    /**
     * Getter a string representation of the water type's position in the spinner.
     * @return a string representation of the water type's position in the spinner.
     */
    public int getPosition() {

        return position;
    }
}

