/*
 * Set a task for a database modification
 *
 */
package com.example.mobile_pj2.Control;

import com.example.mobile_pj2.Data.DataManager;

public class SubmitTask implements Runnable {

    private int number;   // -1 for increase, 1 for decrease
    private String buildingName;

    public SubmitTask(int number, String buildingName){
        this.number = number;
        this.buildingName = buildingName;
    }

    @Override
    public void run() {
        DataManager dataManager = new DataManager();
        dataManager.ModifyPeopleInside(buildingName,number);
    }
}
