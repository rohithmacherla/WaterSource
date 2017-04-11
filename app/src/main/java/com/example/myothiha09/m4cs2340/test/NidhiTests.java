package com.example.myothiha09.m4cs2340.test;

import com.example.myothiha09.m4cs2340.controller.GraphActivity;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NidhiTests {
    private static final int TIMEOUT = 200;






    @Test(timeout = TIMEOUT)
    public void postiveDistance() {
        double d = GraphActivity.distance(27.9, 34, 89.9, 43);

        assertEquals(62.64, d, 1e-2);
    }

    @Test(timeout = TIMEOUT)
    public void negativeDistance() {
        double d = GraphActivity.distance(-27.9, -34, -89.9, -43);

        assertEquals(62.64, d, 1e-2);
    }

    @Test(timeout = TIMEOUT)
    public void negativelatDistance() {
        double d = GraphActivity.distance(-27.9, 34, -89.9, 43);

        assertEquals(62.64, d, 1e-2);
    }

    @Test(timeout = TIMEOUT)
    public void negativelongDistance() {
        double d = GraphActivity.distance(27.9, -34, 89.9, -43);

        assertEquals(62.64, d, 1e-2);
    }

    @Test(timeout = TIMEOUT)
    public void badlongDistance() {
        try {
            double d = GraphActivity.distance(27.9, -34, 97, -43);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            throw e;
        }

    }

    @Test(timeout = TIMEOUT)
    public void badlatDistance() {
        try {
            double d = GraphActivity.distance(27.9, -34, 70, 190);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            throw e;
        }

    }

    @Test(timeout = TIMEOUT)
    public void endBoundsDistance() {

        double d = GraphActivity.distance(90, 180, -90, -180);
        assertEquals(402.49, d, 1e-2);

    }

    @Test(timeout = TIMEOUT)
    public void justOverEndBoundsDistance() {

        try {
            double d = GraphActivity.distance(90.1, 180.1, -90.1, -180.1);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            throw e;
        }
    }

    @Test(timeout = TIMEOUT)
    public void justUnderEndBoundsDistance() {

        double d = GraphActivity.distance(89.9, 179.9, -89.9, -179.9);
        assertEquals(402.22, d, 1e-2);

    }




}
