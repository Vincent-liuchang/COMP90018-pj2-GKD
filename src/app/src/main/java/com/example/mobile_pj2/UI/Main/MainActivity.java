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

          Building oldEngineering = new Building("Old Engineering",
                new Point(144959078, -37798728),
                new Point(144962223, -37799331),
                new Point(144961319, -37799676),
                new Point(144962141, -37799811));
        Building Euson = new Building("Euson",
                new Point(144959266, -37800913),
                new Point(144959607, -37800960),
                new Point(144959013, -37801914),
                new Point(144959387, -37801931));
        Building lawBuilding = new Building("Law Building",
                new Point(144959654, -37802123),
                new Point(144960485, -37802229),
                new Point(144959680, -37802598),
                new Point(144960458, -37802647));
        Building baillieuLibrary = new Building("Baillieu Library",
                new Point(144959138, -37798235),
                new Point(144959583, -37798277),
                new Point(144959078, -37798728),
                new Point(144959514, -37798764));
        Building ERC = new Building("ERC",
                new Point(14462644,  -37798888),
                new Point(144963333, -37798962),
                new Point(144962555, -37799456),
                new Point(144963135, -37799537));
        Building biomedicalLibrary = new Building("Biomedical Library",
                new Point(144959331, -37798801),
                new Point(144959674, -37798826),
                new Point(144959298, -37799133),
                new Point(144959631, -37799152));

        buildingList = new ArrayList<>();
        buildingList.add(oldEngineering);
        buildingList.add(Euson);
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


//    https://firebase.google.com/docs/firestore/query-data/get-data?authuser=0

}
