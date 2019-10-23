/*
 * Create ThreadPool
 * Start All initial tasks
 */
package com.example.mobile_pj2.Control;

import android.content.Context;
import android.os.Handler;


import com.example.mobile_pj2.Data.UpdateCallback;
import com.example.mobile_pj2.Data.Model.Building;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainController {

    private Context context;
    private ThreadPoolExecutor myPool;
    private Handler mainHandler;

    public MainController(Handler mainHandler,Context context, CopyOnWriteArrayList<Building> buildingArrayList, UpdateCallback updateCallback){
        this.mainHandler = mainHandler;
        this.context = context;
        this.myPool = new ThreadPoolExecutor(5, 5,
                10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());

        FusedLocationListener fusedLocationListener = new FusedLocationListener(mainHandler,context,buildingArrayList,this);
        AccelerometerListener accelerometerListener = new AccelerometerListener(mainHandler,context);
        DatabaseListener databaseListener = new DatabaseListener(buildingArrayList, updateCallback);
        myPool.execute(databaseListener);
        myPool.execute(fusedLocationListener);
        myPool.execute(accelerometerListener);
        System.out.println("myPool Status" + myPool.toString());
    }

    public ThreadPoolExecutor getMyPool(){
        return myPool;
    }
}
