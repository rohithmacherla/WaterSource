package com.example.myothiha09.m4cs2340.model;

import java.io.Serializable;

/**
 * Created by nidhi on 3/15/17.
 */

public enum OverallCondition implements Serializable {



    SAFE("Safe", 0),
    TREATABLE("Treatable", 1),
    UNSAFE("Unsafe", 2);
    //POTABLE("Potable", 3);

    private String name;
    private int position;

    /**
     * Constructor for the enum WaterConditon
     * @param n
     * @param p
     */
    private OverallCondition(String n, int p) {
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
