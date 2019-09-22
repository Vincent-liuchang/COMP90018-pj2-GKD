package com.example.mobile_pj2.Control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;


public class GpsListener implements Runnable {

    private Context context;
    private final String TAG = getClass().getName();

    public GpsListener(Context context) {
        this.context = context;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void run() {

        final LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                System.out.println(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude()+" "+locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        Looper.prepare();
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5, 5, locationListener,Looper.myLooper());
        Looper.loop();

    }
}
