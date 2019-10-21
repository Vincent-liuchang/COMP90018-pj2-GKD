package com.example.mobile_pj2.Control;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static android.content.Context.SENSOR_SERVICE;

public class AccelerometerListener implements Runnable, SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Context context;
    private Handler handler;

    public AccelerometerListener(Handler handler, Context context){
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void run() {

        mSensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        if (mSensorManager == null) {
            Log.d(TAG, "device does not support SensorManager");
        } else {
            //  G-Sensor
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == null)
            return;

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            int x = (int) event.values[0];
            int y = (int) event.values[1];
            int z = (int) event.values[2];

            if(x==0 && y ==0){
                Message message = new Message();
                message.what = 2;
                handler.sendMessageDelayed(message,1000);
//                System.out.println("lay down your phone");
            }else{
                Message message = new Message();
                message.what = 3;
                handler.sendMessageDelayed(message,1000);
//                System.out.println("pick up your phone");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
