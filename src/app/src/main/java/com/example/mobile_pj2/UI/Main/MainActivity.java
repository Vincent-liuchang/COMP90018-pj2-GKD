package com.example.mobile_pj2.UI.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mobile_pj2.Control.MainController;
import com.example.mobile_pj2.Data.*;
import com.example.mobile_pj2.Data.Model.*;
import com.example.mobile_pj2.R;
import com.example.mobile_pj2.UI.Info.LibInfoActivity;

import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private CopyOnWriteArrayList<Building> buildingList = null;
    private BuildingAdapter buildingAdapter = null;
    private Context mContext;
    private FragmentTwo fg2;
    private FragmentThree fg1,fg3,fg4;
    public static final int UpdateInterface = 1;
    public static final int StartTimmer = 2;
    public static final int PauseTimmer = 3;
    private Timer timer;
    private long startTime = 0;

    @SuppressLint("HandlerLeak")
    private Handler  mainHandler = new Handler(){
        public void handleMessage(Message message){
            switch(message.what){
                case UpdateInterface:
                    refreshUI();break;
                case StartTimmer:
                    startTimer();break;
                case PauseTimmer:
                    clearTimer();break;
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
        if(fg3 != null && fg3.getView() != null) {
            TextView textView_building = fg3.getView().findViewById(R.id.where);
            TextView textView_number = fg3.getView().findViewById(R.id.num_with_you);
            for (Building building : buildingList) {
                if (building.getInside()) {
                    textView_building.setText(building.getBuildingName());
                    textView_number.setText(String.valueOf(building.getPeopleInside()));
                    break;
                }
                textView_building.setText("No Building");
                textView_number.setText("0");
            }
        }
    }

    public void startTimer(){
        if(fg3 != null && fg3.getView() != null) {
            if(startTime == 0) {
                startTime = System.currentTimeMillis();
            }
            long timePast = (System.currentTimeMillis()- startTime)/1000;
            int time0[] = transformTime(timePast);
            int time1[] = transformTime(System.currentTimeMillis()/1000);
            System.out.println(time1[0]+ " "+time1[1]);
            time1[0] = Math.max(20 - time1[0],0);
            time1[1] = 60 - time1[1];

            TextView textView_time = fg3.getView().findViewById(R.id.my_state_time);
            TextView textView_timeLeft = fg3.getView().findViewById(R.id.state_timeLeft);
            textView_time.setText(String.format("%02d",time0[0])+" : "+String.format("%02d",time0[1])+" : " + String.format("%02d",time0[2]));
            textView_timeLeft.setText(time1[0]+" hour "+time1[1]+" minute");
        }
    }

    public void clearTimer(){
        if(fg3 != null && fg3.getView() != null) {
            startTime = System.currentTimeMillis();
            int time1[] = transformTime(startTime);
            System.out.println(time1[0] + " " + time1[1]);
            time1[0] = Math.max(20 - time1[0],0);
            time1[1] = 60 - time1[1];
            TextView textView_timeLeft = fg3.getView().findViewById(R.id.state_timeLeft);
            textView_timeLeft.setText(time1[0] + " hour " + time1[1] + " minute");
        }

        if (startTime != 0) {
            this.startTime = 0;
            TextView textView_time = fg3.getView().findViewById(R.id.my_state_time);
            textView_time.setText("00 : 00 : 00");
            System.out.println("stop timing");
        }
    }

    public int[] transformTime(long time){
        int seconds = (int)time % 60;
        int minutes = (int)((time/60)%60);
        int hours = (int)(((time/60)/60)%60);
        int a[] = {hours,minutes,seconds};
        return a;
    }

    private void bindViews(){

        RadioGroup rg_tab_bar = findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        RadioButton rb_activity = findViewById(R.id.rb_library);
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
                    fg1 = new FragmentThree("2");
                    fTransaction.add(R.id.fragment_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                break;
            case R.id.rb_library:
                if(fg2 == null){
                    fg2 = new FragmentTwo(this.buildingAdapter,MainActivity.this);
                    fTransaction.add(R.id.fragment_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
            break;
            case R.id.rb_myState:
                if(fg3 == null){
                    fg3 = new FragmentThree("No Building");
                    fTransaction.add(R.id.fragment_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
            case R.id.rb_bottle:
                if(fg4 == null){
                    fg4 = new FragmentThree("4");
                    fTransaction.add(R.id.fragment_content,fg4);
                }else{
                    fTransaction.show(fg4);
                }
                break;
        }
        fTransaction.commit();
        refreshUI();
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
    }


}
