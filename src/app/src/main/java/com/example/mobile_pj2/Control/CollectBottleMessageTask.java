package com.example.mobile_pj2.Control;

import com.example.mobile_pj2.Data.DataManager;
import com.example.mobile_pj2.UI.Bottole.ReceiveCallBack;

public class CollectBottleMessageTask implements Runnable {
    private String buildingName;
    private ReceiveCallBack updateCallback;

    public CollectBottleMessageTask(String buildingName){
        this.buildingName = buildingName;
    }

    public void setUpdateCallback(ReceiveCallBack updateCallback) {
        this.updateCallback = updateCallback;
    }

    @Override
    public void run() {
        DataManager dataManager = new DataManager();
        dataManager.GetAnUnreadBottle(buildingName,updateCallback);
    }

}
