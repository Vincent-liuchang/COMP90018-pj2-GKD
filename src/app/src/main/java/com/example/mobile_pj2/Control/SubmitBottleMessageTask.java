package com.example.mobile_pj2.Control;

import com.example.mobile_pj2.Data.DataManager;

public class SubmitBottleMessageTask implements Runnable {

    private String content;
    private String buildingName;

    public SubmitBottleMessageTask(String content, String buildingName){
        this.content = content;
        if(buildingName == null)
            buildingName = "???";
        this.buildingName = buildingName;
    }

    @Override
    public void run() {
        DataManager dataManager = new DataManager();
        dataManager.AddMessageToDatabase("Kevin",content,buildingName);
    }
}
