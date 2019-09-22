package com.example.mobile_pj2.UI.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mobile_pj2.Control.MainController;
import com.example.mobile_pj2.Data.*;
import com.example.mobile_pj2.Data.Model.*;
import com.example.mobile_pj2.R;

import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity {
    String TAG = getClass().getName();

    private CopyOnWriteArrayList<Building> buildingList = null;
    private BuildingAdapter buildingAdapter = null;
    private Context mContext = null;
    private ListView list_main = null;
    private Button button = null;
    private  TextView textView =null;

    public static  final int UpdateInterface = 1;

    private Handler  mainHandler = new Handler(){
        public void handleMessage(Message message){
            switch(message.what){
                case UpdateInterface:
                    refreshUI();
                    System.out.println("received Update request");break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        this.addBuildings();

        MainController mainController = new MainController(this.mainHandler, this.mContext, buildingList,new UpdateCallback() {
            @Override
            public void update() {
                buildingAdapter.update();
            }
        });

        buildingAdapter = new BuildingAdapter(buildingList,mContext);
        bindViews();
        list_main.setAdapter(buildingAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               refreshUI();
            }
        });

    }

    private void refreshUI(){
        buildingAdapter.update();
        for(Building building:buildingList) {
            if(building.getInside()) {
                textView.setText(building.getBuildingName());
                break;
            }
            textView.setText("no Building");
        }
    }

    private void bindViews(){
        list_main = findViewById(R.id.list_main);
        button = findViewById(R.id.button_test);
        textView = findViewById(R.id.where);
    }

    private void addBuildings(){

        buildingList = new CopyOnWriteArrayList<>();

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
        Building lawLibrary = new Building("LawLibrary",
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
                new GeoPoint(144.962644,  -37.798888),
                new GeoPoint(144.963333, -37.798962),
                new GeoPoint(144.962555, -37.799456),
                new GeoPoint(144.963135, -37.799537));
        Building biomedicalLibrary = new Building("BiomedicalLibrary",
                new GeoPoint(144.959331, -37.798801),
                new GeoPoint(144.959674, -37.798826),
                new GeoPoint(144.959298, -37.799133),
                new GeoPoint(144.959631, -37.799152));

        buildingList.add(msd);
        buildingList.add(giblinEunson);
        buildingList.add(lawLibrary);
        buildingList.add(ERC);
        buildingList.add(baillieuLibrary);
        buildingList.add(biomedicalLibrary);

    }
}
