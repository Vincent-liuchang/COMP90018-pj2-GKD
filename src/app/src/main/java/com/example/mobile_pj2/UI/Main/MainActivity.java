package com.example.mobile_pj2.UI.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mobile_pj2.Control.MainController;
import com.example.mobile_pj2.Data.*;
import com.example.mobile_pj2.Data.Model.*;
import com.example.mobile_pj2.R;

import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private CopyOnWriteArrayList<Building> buildingList = null;
    private BuildingAdapter buildingAdapter = null;
    private Context mContext;
    private FragmentOne fg1;
    private FragmentTwo fg2,fg3,fg4;
    public static  final int UpdateInterface = 1;

    private RadioGroup rg_tab_bar;
    private RadioButton rb_activity;

    @SuppressLint("HandlerLeak")
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
        Configuration.setConfiguration(mContext);
        this.addBuildings();
        MainController mainController = new MainController(this.mainHandler, this.mContext, buildingList,new UpdateCallback() {
            @Override
            public void update() {
                buildingAdapter.update();
            }
        });

        buildingAdapter = new BuildingAdapter(buildingList,mContext);
        bindViews();


    }

    private void refreshUI(){
        buildingAdapter.update();
        TextView textView = fg1.getView().findViewById(R.id.where);
        for(Building building:buildingList) {
            if(building.getInside()) {
                textView.setText(building.getBuildingName());
                break;
            }
            textView.setText("No Building");
        }
    }

    private void bindViews(){

        rg_tab_bar = findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        rb_activity = findViewById(R.id.rb_activities);
        rb_activity.setChecked(true);
    }

    private void addBuildings(){

        buildingList = new CopyOnWriteArrayList<>();

        for (String building: Configuration.getConfiguration().keySet()){
            buildingList.add(new Building(building));
        }

    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fTransaction);
        switch (checkedId){
            case R.id.rb_activities:
                if(fg1 == null){
                    fg1 = new FragmentOne(this.buildingAdapter);
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_content,fg1).commit();
                }else{
                    fTransaction.show(fg1);
                }
                break;
            case R.id.rb_library:
                if(fg2 == null){
                    fg2 = new FragmentTwo("2");
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_content,fg2).commit();
                }else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.rb_myState:
                if(fg3 == null){
                    fg3 = new FragmentTwo("3");
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_content,fg3).commit();
                }else{
                    fTransaction.show(fg3);
                }
                break;
            case R.id.rb_bottle:
                if(fg4 == null){
                    fg4 = new FragmentTwo("4");
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_content,fg4).commit();
                }else{
                    fTransaction.show(fg4);
                }
                break;
        }
        fTransaction.commit();
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
    }


}
