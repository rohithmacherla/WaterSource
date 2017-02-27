package com.example.myothiha09.m4cs2340.model;

/**
 * Created by vgiridhar on 2/26/17.
 */

public enum WaterCondition {
    WASTE("Waste", 0),
    TREATABLE_CLEAR("Treatable-Clear", 1),
    TREATABLE_MUDDY("Treatable-Muddy", 2),
    POTABLE("Potable", 3);

    private String name;
    private int position;

    private WaterCondition(String n, int p) {
        name = n;
        position = p;
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}


