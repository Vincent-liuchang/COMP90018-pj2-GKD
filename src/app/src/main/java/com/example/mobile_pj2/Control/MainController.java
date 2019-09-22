/*
 * Create ThreadPool
 * Start All initial tasks
 */
package com.example.mobile_pj2.Control;

import android.content.Context;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainController {

    Context context;
    ThreadPoolExecutor myPool;

    public MainController(Context context){
        this.context = context;
        this.myPool = new ThreadPoolExecutor(5, 5,
                10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());

        myGpsListener myGpsListener = new myGpsListener(context);
        myPool.execute(myGpsListener);
    }

    public ThreadPoolExecutor getMyPool(){
        return myPool;
    }
}
