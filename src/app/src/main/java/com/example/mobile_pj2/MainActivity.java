package com.example.mobile_pj2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataManager = new DataManager();

        this.addBuildings();

        mContext = MainActivity.this;
        bindViews();

        buildingAdapter = new BuildingAdapter(buildingList,mContext);
        list_main.setAdapter(buildingAdapter);

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
