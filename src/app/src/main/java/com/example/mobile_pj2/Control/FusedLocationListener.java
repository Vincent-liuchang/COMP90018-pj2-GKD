package com.example.mobile_pj2.Control;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.mobile_pj2.Data.Model.Building;
import com.example.mobile_pj2.Data.Model.GeoPoint;
import com.example.mobile_pj2.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class FusedLocationListener implements Runnable{
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private boolean mTrackingLocation = true;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private Context context;
    private final String TAG = getClass().getName();
    private String provider;
    private CopyOnWriteArrayList<Building> buildingList;
    private MainController mainController;
    private Handler handler;

    public FusedLocationListener(Handler handler, Context context, CopyOnWriteArrayList<Building> buildingList, MainController mainController) {
        this.handler = handler;
        this.context = context;
        this.buildingList = buildingList;
        this.mainController = mainController;
    }
    @Override
    public void run() {


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);


        System.out.println("test 1");

        mLocationCallback = new LocationCallback() {
            /**
             * This is the callback that is triggered when the
             * FusedLocationClient updates your location.
             * @param locationResult The result containing the device location.
             */
            @Override
            public void onLocationResult(LocationResult locationResult) {
                System.out.println("test 2");

                if(mTrackingLocation){
                    System.out.println("test 3");
                    System.out.println("latitude: " + locationResult.getLocations().get(0).getLatitude() + ", longitude: " + locationResult.getLocations().get(0).getLongitude());
                    Location location = locationResult.getLocations().get(0);
                    updateLocation(location);

                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            }

        };

        startTrackingLocation();

    }


    @SuppressLint("MissingPermission")
    private void startTrackingLocation() {
        Looper.prepare();

        mFusedLocationClient.requestLocationUpdates(getLocationRequest(),
                            mLocationCallback,
                            Looper.getMainLooper() /* Looper */);
    }

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }


    private void updateLocation(Location location) {
        double x = location.getLongitude();
        double y = location.getLatitude();
        System.out.println("x is " + x);
        System.out.println("y is " + y);
        GeoPoint myLocation = new GeoPoint(x,y);
        for(Building building: this.buildingList) {
            building.calculateDistanse(myLocation);
            if(building.getInside()){
                if(!building.isInside(myLocation)){
                    mainController.getMyPool().execute(new SubmitTask(-1,building.getBuildingName()));
                    building.setInside(false);
                }
            }else{
                if(building.isInside(myLocation)){
                    mainController.getMyPool().execute(new SubmitTask(1,building.getBuildingName()));
                    building.setInside(true);
                }
            }
        }
        Collections.sort(buildingList);
    }
}
