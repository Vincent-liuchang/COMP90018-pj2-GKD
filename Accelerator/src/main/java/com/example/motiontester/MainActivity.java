package com.example.motiontester;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "AccelerometerSensorDemo";

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private TextView textViewMotion;
    private TextView textViewTime;
    private TextView textViewX;
    private TextView textViewY;
    private TextView textViewZ;

    private int pastX = 0;
    private int pastY = 0;
    private int pastZ = 9;

    Handler handler;

    private int m =1;

    private boolean timeOn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewTime=(TextView) findViewById(R.id.text_time);
        textViewMotion = (TextView) findViewById(R.id.text_motion);
        textViewX = (TextView) findViewById(R.id.text_x);
        textViewY = (TextView) findViewById(R.id.text_y);
        textViewZ = (TextView) findViewById(R.id.text_z);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (mSensorManager == null) {
            Log.d(TAG, "device does not support SensorManager");
        } else {
            //  G-Sensor
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener((SensorEventListener) this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        handler = new UpdateHandler();
//        handler.sendEmptyMessageDelayed(1, 1000);//start after 1000

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
            mSensorManager = null;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == null)
            return;

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            int x = (int) event.values[0];
            int y = (int) event.values[1];
            int z = (int) event.values[2];

            textViewX.setText(String.valueOf(pastX) + "  " + String.valueOf(x));
            textViewY.setText(String.valueOf(pastY) + "  " + String.valueOf(y));
            textViewZ.setText(String.valueOf(pastZ) + "  " + String.valueOf(z));

            if(x==0 && y ==0){
                textViewMotion.setText("not moving");
                startTime();
            }else{
                textViewMotion.setText("moving");
                pauseTime();
            }

            pastX = x;
            pastY = y;
            pastZ = z;
        }
    }

    public void startTime(){
        if(timeOn==false){
            timeOn = true;
            handler.sendEmptyMessageDelayed(1, 1000);
        }
    }

    public void pauseTime(){
        timeOn = false;
    }




    class UpdateHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    textViewTime=(TextView) findViewById(R.id.text_time);
                    textViewTime.setText("time : " +m);
                    m = m + 1;
                    if(pastX ==0 && pastY == 0){
                        sendEmptyMessageDelayed(1, 1000); //send again after 1000
                    }
                    break;

                default:
                    break;
            }

        }
    }


//    public int getMaxValue(int x, int y, int z) {
//        int max = 0;
//        if (x >= y && x >= z) {
//            max = x;
//        } else if (y >= x && y >= z) {
//            max = y;
//        } else if (z >= x && z >= y) {
//            max = z;
//        }
//
//        return max;
//    }



}
