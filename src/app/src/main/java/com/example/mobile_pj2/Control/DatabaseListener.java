package com.example.mobile_pj2.Control;

import com.example.mobile_pj2.Data.DataManager;
import com.example.mobile_pj2.Data.UpdateCallback;
import com.example.mobile_pj2.Data.Model.Building;

import java.util.concurrent.CopyOnWriteArrayList;

public class DatabaseListener implements Runnable {
    private CopyOnWriteArrayList<Building> buildingArrayList;
    UpdateCallback updateCallback;

    public DatabaseListener(CopyOnWriteArrayList<Building> buildingArrayList, UpdateCallback updateCallback){
        this.updateCallback = updateCallback;
        this.buildingArrayList = buildingArrayList;
    }
    @Override
    public void run() {
        DataManager dataManager = new DataManager();
        for(Building building:buildingArrayList) {
            dataManager.ListenPeopleInside(building, updateCallback);
        }

    }
}
