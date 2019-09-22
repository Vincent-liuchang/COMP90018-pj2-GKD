package com.example.gps;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Context;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.GpsStatus;
import android.location.Location;

import android.location.LocationListener;

import android.location.LocationManager;

import android.os.Build;
import android.os.Bundle;

import android.os.Vibrator;

import android.util.Log;

import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;


public class MainActivity extends Activity {

    /** Called when the activity is first created. */

    private LocationManager manager;

    TextView textView;
    TextView build;

    private static final String TAG = "LOCATION DEMO";
    Building MSD = new Building("MSD",
            new GeoPoint(144.962394, -37.796850),
            new GeoPoint(144.963180, -37.796937),
            new GeoPoint(144.962290, -37.797430),
            new GeoPoint(144.963132, -37.797519));
    Building Euson = new Building("Euson",
            new GeoPoint(144.959266, -37.800913),
            new GeoPoint(144.959607, -37.800960),
            new GeoPoint(144.959013, -37.801914),
            new GeoPoint(144.959387, -37.801931));
    Building lawBuilding = new Building("Law Building",
            new GeoPoint(144.959654, -37.802123),
            new GeoPoint(144.960485, -37.802229),
            new GeoPoint(144.959680, -37.802598),
            new GeoPoint(144.960458, -37.802647));
    Building baillieuLibrary = new Building("Baillieu Library",
            new GeoPoint(144.959138, -37.798235),
            new GeoPoint(144.959583, -37.798277),
            new GeoPoint(144.959078, -37.798728),
            new GeoPoint(144.959514, -37.798764));
    Building ERC = new Building("ERC",
            new GeoPoint(144.62644,  -37.798888),
            new GeoPoint(144.963333, -37.798962),
            new GeoPoint(144.962555, -37.799456),
            new GeoPoint(144.963135, -37.799537));
    Building biomedicalLibrary = new Building("Biomedical Library",
            new GeoPoint(144.959331, -37.798801),
            new GeoPoint(144.959674, -37.798826),
            new GeoPoint(144.959298, -37.799133),
            new GeoPoint(144.959631, -37.799152));

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text1);
        build = findViewById(R.id.text2);

        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        double x = location.getLongitude();
        double y = location.getLatitude();
        if(lawBuilding.isInside(new GeoPoint(x, y)))
            build.setText("Lawbuilding");
        else if(MSD.isInside(new GeoPoint(x, y)))
            build.setText("MSD");
        else if(Euson.isInside(new GeoPoint(x, y)))
            build.setText("Euson");
        else if(baillieuLibrary.isInside(new GeoPoint(x, y)))
            build.setText("Baillieu");
        else if(ERC.isInside(new GeoPoint(x, y)))
            build.setText("ERC");
        else if(biomedicalLibrary.isInside(new GeoPoint(x, y)))
            build.setText("BiomedicalLibrary");
        else build.setText("No building");


        updateLocation(location);


        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 5, locationListener);



    }


//    public void onPause(){
//
//        super.onPause();
//
//        manager.removeGpsStatusListener((GpsStatus.Listener) locationListener);

//    }




    private final LocationListener locationListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            //here
            updateLocation(location);
            int x = (int)(location.getLongitude()* 1000000);
            int y = (int)(location.getLatitude()* 1000000);
            if(lawBuilding.isInside(new GeoPoint(x, y)))
                build.setText("Lawbuilding");
            else if(MSD.isInside(new GeoPoint(x, y)))
                build.setText("Old Engineering");
            else if(Euson.isInside(new GeoPoint(x, y)))
                build.setText("Euson");
            else if(baillieuLibrary.isInside(new GeoPoint(x, y)))
                build.setText("Baillieu");
            else if(ERC.isInside(new GeoPoint(x, y)))
                build.setText("ERC");
            else if(biomedicalLibrary.isInside(new GeoPoint(x, y)))
                build.setText("BiomedicalLibrary");
            else build.setText("No building");
            //

        }

        public void onProviderDisabled(String provider){

            updateLocation(null);

            Log.i(TAG, "Provider now is disabled..");

        }

        public void onProviderEnabled(String provider){

            Log.i(TAG, "Provider now is enabled..");

        }

        public void onStatusChanged(String provider, int status,Bundle extras){ }

    };

    private void updateLocation(Location location) {

        String latLng;

        if (location != null) {

            double lat = location.getLatitude();

            double lng = location.getLongitude();



            latLng = "Latitude:" + lat + "  Longitude:" + lng;
            textView.setText("Latitude:" + lat + "  Longitude:" + lng );


        } else {

            latLng = "Can't access your location";

        }

        Log.i(TAG, "The location has changed..");

        Log.i(TAG, "Your Location:" +latLng);

    }
