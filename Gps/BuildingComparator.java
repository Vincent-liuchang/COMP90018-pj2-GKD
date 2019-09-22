package com.example.mobile_pj2.Data.Model;

import java.util.Comparator;

public class BuildingComparator implements Comparator<Building> {

    @Override
    public int compare(Building o1, Building o2) {
        return o1.compareTo(o2);
    }
}
