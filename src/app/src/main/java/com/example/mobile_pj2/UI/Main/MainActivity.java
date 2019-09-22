package com.example.mobile_pj2.UI.Main;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.mobile_pj2.Control.MainController;
import com.example.mobile_pj2.Control.SubmitTask;
import com.example.mobile_pj2.Data.*;
import com.example.mobile_pj2.Data.Model.*;
import com.example.mobile_pj2.R;

import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {
        String TAG = getClass().getName();

        private ArrayList<Building> buildingList = null;
        private BuildingAdapter buildingAdapter = null;
        private Context mContext = null;
        private ListView list_main = null;
        private Button button = null;
        private DataManager dataManager = null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            dataManager = new DataManager();

            mContext = MainActivity.this;
            final MainController mainController = new MainController(this.mContext);

            this.addBuildings();
            buildingAdapter = new BuildingAdapter(buildingList,mContext);
            bindViews();
            list_main.setAdapter(buildingAdapter);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG,String.valueOf(mainController.getMyPool().getActiveCount()));


                    mainController.getMyPool().execute(new SubmitTask(1,"MSD"));

                    Log.i(TAG,String.valueOf(mainController.getMyPool().getActiveCount()));
                }
            });

        }

        private  void bindViews(){
            list_main = findViewById(R.id.list_main);
            button = findViewById(R.id.button_test);
        }

        private void addBuildings(){

            Building msd = new Building("MSD", 
                    new GeoPoint(144.962394, -37.796850),
                    new GeoPoint(144.963180, -37.796937),
                    new GeoPoint(144.962290, -37.797430),
                    new GeoPoint(144.963132, -37.797519));
            Building giblinEunson = new Building("GiblinEunsonLibrary",  
                    new GeoPoint(144.959266, -37.800913),
                    new GeoPoint(144.959607, -37.800960),
                    new GeoPoint(144.959013, -37.801914),
                    new GeoPoint(144.959387, -37.801931));
            Building lawBuilding = new Building("LawBuilding", 
                    new GeoPoint(144.959654, -37.802123),
                    new GeoPoint(144.960485, -37.802229),
                    new GeoPoint(144.959680, -37.802598),
                    new GeoPoint(144.960458, -37.802647));
            Building baillieuLibrary = new Building("BaillieuLibrary", 
                    new GeoPoint(144.959138, -37.798235),
                    new GeoPoint(144.959583, -37.798277),
                    new GeoPoint(144.959078, -37.798728),
                    new GeoPoint(144.959514, -37.798764));
            Building ERC = new Building("ERC",
                    new GeoPoint(144.62644,  -37.798888),
                    new GeoPoint(144.963333, -37.798962),
                    new GeoPoint(144.962555, -37.799456),
                    new GeoPoint(144.963135, -37.799537));
            Building biomedicalLibrary = new Building("BiomedicalLibrary",  
                    new GeoPoint(144.959331, -37.798801),
                    new GeoPoint(144.959674, -37.798826),
                    new GeoPoint(144.959298, -37.799133),
                    new GeoPoint(144.959631, -37.799152));

            buildingList = new ArrayList<>();
            buildingList.add(msd);
            buildingList.add(giblinEunson);
            buildingList.add(lawBuilding);
            buildingList.add(ERC);
            buildingList.add(baillieuLibrary);
            buildingList.add(biomedicalLibrary);

            for(Building building:buildingList) {
                this.dataManager.ListenPeopleInside(building, new FireBaseUpdateCallback() {
                    @Override
                    public void update() {
                        buildingAdapter.update();
                    }
                });
            }
        }
}
