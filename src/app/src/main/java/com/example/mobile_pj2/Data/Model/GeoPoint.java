package com.example.mobile_pj2.Data.Model;

import java.io.Serializable;

public class GeoPoint implements Serializable {
    public double x;
    public double y;

    public GeoPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
}