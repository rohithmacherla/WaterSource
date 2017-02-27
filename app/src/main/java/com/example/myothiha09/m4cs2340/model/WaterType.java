package com.example.myothiha09.m4cs2340.model;

import java.io.Serializable;

/**
 * Created by vgiridhar on 2/26/17.
 */

public enum WaterType implements Serializable {
    BOTTLED("Bottled", 0),
    WELL("Well", 1),
    STREAM("Stream", 2),
    LAKE("Lake", 3),
    SPRING("Spring", 4),
    OTHER("Other", 5);


    private String name;
    private int position;

    private WaterType(String n, int p) {
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

