//package com.example.mobile_pj2.UI.Main;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import android.Manifest;
//import android.content.Context;
//import android.os.Bundle;
//
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.example.mobile_pj2.Control.MainController;
//import com.example.mobile_pj2.Data.*;
//import com.example.mobile_pj2.Data.Model.*;
//import com.example.mobile_pj2.R;
//
//import java.util.concurrent.CopyOnWriteArrayList;
//
//public class MainActivity extends AppCompatActivity {
//    String TAG = getClass().getName();
//
//    private CopyOnWriteArrayList<Building> buildingList = null;
//    private BuildingAdapter buildingAdapter = null;
//    private Context mContext = null;
//    private ListView list_main = null;
//    private Button button = null;
//    private  TextView textView =null;
//
//    public static  final int UpdateInterface = 1;
//
//    private Handler  mainHandler = new Handler(){
//        public void handleMessage(Message message){
//            switch(message.what){
//                case UpdateInterface:
//                    refreshUI();
//                    System.out.println("received Update request");break;
//            }
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mContext = MainActivity.this;
//        Configuration.setConfiguration(mContext);
//        this.addBuildings();
//
//        MainController mainController = new MainController(this.mainHandler, this.mContext, buildingList,new UpdateCallback() {
//            @Override
//            public void update() {
//                buildingAdapter.update();
//            }
//        });
//
//        buildingAdapter = new BuildingAdapter(buildingList,mContext);
//        bindViews();
//        list_main.setAdapter(buildingAdapter);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                refreshUI();
//            }
//        });
//
//    }
//
//    private void refreshUI(){
//        buildingAdapter.update();
//        for(Building building:buildingList) {
//            if(building.getInside()) {
//                textView.setText(building.getBuildingName());
//                break;
//            }
//            textView.setText("no Building");
//        }
//    }
//
//    private void bindViews(){
//        list_main = findViewById(R.id.list_main);
//        button = findViewById(R.id.button_test);
//        textView = findViewById(R.id.where);
//    }
//
//    private void addBuildings(){
//
//        buildingList = new CopyOnWriteArrayList<>();
//
//        for (String building: Configuration.getConfiguration().keySet()){
//            buildingList.add(new Building(building));
//        }
//
//    }
//}
