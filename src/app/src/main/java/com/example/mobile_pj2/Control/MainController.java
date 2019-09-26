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

        GpsListener GpsListener = new GpsListener(mainHandler,context,buildingArrayList,this);
        DatabaseListener databaseListener = new DatabaseListener(buildingArrayList, updateCallback);
        MotionController motionController =  new MotionController(mainHandler,context);
        myPool.execute(databaseListener);
        myPool.execute(GpsListener);
        myPool.execute(motionController);

        System.out.println(myPool.toString());
    }

    public ThreadPoolExecutor getMyPool(){
        return myPool;
    }
}
