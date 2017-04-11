package com.example.myothiha09.m4cs2340.test;

import com.example.myothiha09.m4cs2340.controller.MapsActivity;
import com.google.android.gms.maps.model.LatLng;

import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Kevin on 4/10/2017.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KevinTests {
    private static final int TIMEOUT = 200;

    //Correct input
    @Test(timeout = TIMEOUT)
    public void correctLatLng() {
        String latLng = "lat/lng: (82.010891637586887,105.802413038909435)";
        LatLng result = MapsActivity.convertStringtoLatLng(latLng);
        assertTrue(result.latitude == 82.01);
        assertTrue(result.longitude == 105.80);
    }

    //Totally incorrect input
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void incorrectInput() {
        String latLng = "Kevin > Arshad and 1332 > 2340";
        try {
            MapsActivity.convertStringtoLatLng(latLng);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            throw e;
        }
    }

    //Partitioning the cases.
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void tooSmallLatCorrectLong() {
        String latLng = "lat/lng: (-220.100891637586887,5.802413038909435)";
        try {
            MapsActivity.convertStringtoLatLng(latLng);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            throw e;
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void tooLargeLatCorrectLong() {
        String latLng = "lat/lng: (220.100891637586887,5.802413038909435)";
        try {
            MapsActivity.convertStringtoLatLng(latLng);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            throw e;
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void correctLatTooSmallLng() {
        String latLng = "lat/lng: (80.100891637586887,-251.802413038909435)";
        try {
            MapsActivity.convertStringtoLatLng(latLng);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            throw e;
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void correctLatTooLargeLng() {
        String latLng = "lat/lng: (80.100891637586887,254.802413038909435)";
        try {
            MapsActivity.convertStringtoLatLng(latLng);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            throw e;
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void tooSmallLatTooSmallLng() {
        String latLng = "lat/lng: (-250.100891637586887,-251.802413038909435)";
        try {
            MapsActivity.convertStringtoLatLng(latLng);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            throw e;
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void tooSmallLatTooLargeLng() {
        String latLng = "lat/lng: (-250.100891637586887,251.802413038909435)";
        try {
            MapsActivity.convertStringtoLatLng(latLng);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            throw e;
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void boundarySmallLatCorrectLng() {
        String latLng = "lat/lng: (-90.01,25.802413038909435)";
        try {
            MapsActivity.convertStringtoLatLng(latLng);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            throw e;
        }
    }

    @Test(timeout = TIMEOUT)
    public void boundaryCorrectLatLng() {
        String latLng = "lat/lng: (-90.00000000000000000,-180.000000000000000)";
        LatLng result = MapsActivity.convertStringtoLatLng(latLng);
        assertTrue(result.latitude == -90.00);
        assertTrue(result.longitude == -180.00);
    }

    @Test(timeout = TIMEOUT)
    public void boundaryCorrectLatLng2() {
        String latLng = "lat/lng: (-90.00000000000000000,179.99000000000)";
        LatLng result = MapsActivity.convertStringtoLatLng(latLng);
        assertTrue(result.latitude == -90.00);
        assertTrue(result.longitude == 179.99);
    }

    @Test(timeout = TIMEOUT)
    public void boundaryCorrectLatLng3() {
        String latLng = "lat/lng: (90.00000000000000000,179.99000000000)";
        LatLng result = MapsActivity.convertStringtoLatLng(latLng);
        assertTrue(result.latitude == 90.00);
        assertTrue(result.longitude == 179.99);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void boundaryCorrectLatLargeLng() {
        String latLng = "lat/lng: (-90.0000000000,180.000000000000000000000)";
        try {
            MapsActivity.convertStringtoLatLng(latLng);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            throw e;
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void boundaryLargeLatLargeLng() {
        String latLng = "lat/lng: (90.0100000000,180.000000000000000000000)";
        try {
            MapsActivity.convertStringtoLatLng(latLng);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            throw e;
        }
    }

}
