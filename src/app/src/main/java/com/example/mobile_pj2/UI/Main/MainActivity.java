package com.example.mobile_pj2.UI.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.mobile_pj2.Control.MainController;
import com.example.mobile_pj2.Data.*;
import com.example.mobile_pj2.Data.Model.*;
import com.example.mobile_pj2.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Building> buildingList =null;
    private BuildingAdapter buildingAdapter =null;
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

        this.addBuildings();

        mContext = MainActivity.this;
        bindViews();

        buildingAdapter = new BuildingAdapter(buildingList,mContext);

        list_main.setAdapter(buildingAdapter);

        MainController mainController = new MainController(this.mContext);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildingAdapter.update();
            }
        });

    }

    private  void bindViews(){
        list_main = findViewById(R.id.list_main);
        button = findViewById(R.id.button_test);
    }

    private void addBuildings(){

        Building oldEngineering = new Building("Old Engineering");
        Building oldArts = new Building("Old Arts");
        Building lawBuilding = new Building("Law Building");
        Building baillieuLibrary = new Building("Baillieu Library");
        Building ERC = new Building("ERC");

        buildingList = new ArrayList<>();
        buildingList.add(oldEngineering);
        buildingList.add(oldArts);
        buildingList.add(lawBuilding);
        buildingList.add(ERC);
        buildingList.add(baillieuLibrary);

        for(Building building:buildingList) {
            this.dataManager.ListenPeopleInside(building, new FireBaseUpdateCallback() {
                @Override
                public void update() {
                    buildingAdapter.update();
                }
            });
        }

    }


//    https://firebase.google.com/docs/firestore/query-data/get-data?authuser=0

}
