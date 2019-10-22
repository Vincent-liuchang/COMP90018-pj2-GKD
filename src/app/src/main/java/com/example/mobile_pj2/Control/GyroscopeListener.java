package com.example.mobile_pj2.Control;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;

public class GyroscopeListener implements Runnable {
    private SensorManager sensorManager;
    private SensorEventListener shakeListener;
    private Context context;

    private Handler handler;

    public GyroscopeListener(Handler handler, Context context) {
        this.handler = handler;
        this.context = context;
    }

    @Override
    public void run() {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        shakeListener = new ShakeSensorListener();

        sensorManager.registerListener(shakeListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_UI);
    }

    private class ShakeSensorListener implements SensorEventListener {
        private static final int G = 10;
        @Override
        public void onSensorChanged(SensorEvent event) {

            float[] values = event.values;
            float x = Math.abs(values[0]);
            float y = Math.abs(values[1]);
            float z = Math.abs(values[2]);

            if (x >= G || y >= G
                    || z >= G) {
                Message message = new Message();
                message.what = 4;
                handler.sendMessage(message);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }
    }


}
