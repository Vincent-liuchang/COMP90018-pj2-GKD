package com.example.mobile_pj2.Control;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.Toast;

import com.example.mobile_pj2.Data.Model.Building;

import java.util.concurrent.CopyOnWriteArrayList;

public class VibrationHelper implements Runnable {
    private SensorManager sensorManager;
    private SensorEventListener shakeListener;
    private AlertDialog.Builder dialogBuilder;
    private Context context;
    private final String TAG = getClass().getName();
    private String provider;

    private Handler handler;
    private long lastTime;
    private Animation rotateAnimation;

    private boolean isRefresh = false;

    public VibrationHelper(Handler handler, Context context) {
        this.handler = handler;
        this.context = context;


    }
    @Override
    public void run() {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        shakeListener = new ShakeSensorListener();

        sensorManager.registerListener(shakeListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);

//        dialogBuilder = new AlertDialog.Builder(context);
//        dialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                isRefresh = false;
//                dialog.cancel();
//            }
//        }).setMessage("Message Sent!").create();
    }


    private class ShakeSensorListener implements SensorEventListener {
        private static final int ACCELERATE_VALUE = 30;
        @Override
        public void onSensorChanged(SensorEvent event) {
//            if (isRefresh) {
//                return;
//            }

            float[] values = event.values;
            float x = Math.abs(values[0]);
            float y = Math.abs(values[1]);
            float z = Math.abs(values[2]);

            if (x >= ACCELERATE_VALUE || y >= ACCELERATE_VALUE
                    || z >= ACCELERATE_VALUE) {
                    Message message = new Message();
                    message.what = 4;
                    handler.sendMessage(message);
//                }

//                else {
//                    Toast.makeText(context, "Please Wait For 5 Seconds!", Toast.LENGTH_LONG).show();
//                }

//                isRefresh = true;
//                dialogBuilder.show();
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }
    }


}
