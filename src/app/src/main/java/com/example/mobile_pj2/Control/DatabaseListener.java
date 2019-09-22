package com.example.mobile_pj2.Control;

import android.util.Log;

import com.example.mobile_pj2.Data.DataManager;
import com.example.mobile_pj2.Data.FireBaseUpdateCallback;
import com.example.mobile_pj2.Data.Model.Building;
import com.example.mobile_pj2.UI.Main.BuildingAdapter;

import java.util.ArrayList;

public class DatabaseListener implements Runnable {
    private ArrayList<Building> buildingArrayList;
    FireBaseUpdateCallback fireBaseUpdateCallback;

    public DatabaseListener(ArrayList<Building> buildingArrayList, FireBaseUpdateCallback fireBaseUpdateCallback){
        this.fireBaseUpdateCallback = fireBaseUpdateCallback;
        this.buildingArrayList = buildingArrayList;
    }
    @Override
    public void run() {
        DataManager dataManager = new DataManager();
        for(Building building:buildingArrayList) {
            dataManager.ListenPeopleInside(building,fireBaseUpdateCallback);
        }

    }
}
