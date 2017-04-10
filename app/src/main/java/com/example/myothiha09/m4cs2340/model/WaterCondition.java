package com.example.myothiha09.m4cs2340.model;

// Team 27

import java.io.Serializable;

/**
 * An enum that represents the condition of a water source.
 * Each entry consists of the actual label, and the position in the spinner.
 */
public enum WaterCondition implements Serializable{


    /**
     * First parameter is the string label for each water condition,
     * second parameter is the position the water condition has in the spinner.
     */
    WASTE("Waste", 0),
    TREATABLE_CLEAR("Treatable-Clear", 1),
    TREATABLE_MUDDY("Treatable-Muddy", 2),
    POTABLE("Potable", 3);

    private String name;
    private int position;

    /**
     * Constructor for the enum WaterConditon
     * @param n the name string
     * @param p the position string
     */
    private WaterCondition(String n, int p) {
        name = n;
        position = p;
    }

    /**
     * Return a string representation of the water condition object.
     * @return a string representation of the water condition object.
     */
    public String toString() {

        return name;
    }

    /**
     * Getter a string representation of the water condition name.
     * @return a string representation of the water condition name.
     */
    public String getName() {

        return name;
    }

    /**
     * Getter a string representation of the water condition's position in the spinner.
     * @return a string representation of the water condition's position in the spinner.
     */
    public int getPosition() {

        return position;
    }
}


