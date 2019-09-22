/*
 * Create ThreadPool
 * Start All initial tasks
 */
package com.example.mobile_pj2.Control;

import android.content.Context;
import android.util.Log;

import com.example.mobile_pj2.Data.FireBaseUpdateCallback;
import com.example.mobile_pj2.Data.Model.Building;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainController {

    Context context;
    ThreadPoolExecutor myPool;

    public MainController(Context context, ArrayList<Building> buildingArrayList, FireBaseUpdateCallback fireBaseUpdateCallback){
        this.context = context;
        this.myPool = new ThreadPoolExecutor(5, 5,
                10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());

        GpsListener GpsListener = new GpsListener(context);
        DatabaseListener databaseListener = new DatabaseListener(buildingArrayList,fireBaseUpdateCallback);
        myPool.execute(databaseListener);
        myPool.execute(GpsListener);
    }

    public ThreadPoolExecutor getMyPool(){
        return myPool;
    }
}
