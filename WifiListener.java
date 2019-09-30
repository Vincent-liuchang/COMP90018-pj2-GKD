package com.example.mobile_pj2.Control;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.net.wifi.rtt.RangingRequest;
import android.net.wifi.rtt.RangingResult;
import android.net.wifi.rtt.RangingResultCallback;
import android.net.wifi.rtt.WifiRttManager;
import android.os.Build;
import android.os.Handler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pj2.Data.Model.Building;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WifiListener implements  Runnable{
    private WifiRttManager wifiRttManager;
    private WifiManager wifiManager;
    private Context context;
    private MainController mainController;
    private Handler handler;
    private AppCompatActivity mainActivity;

    public WifiListener(AppCompatActivity mainActivity, Handler handler, Context context, MainController mainController) {
        this.handler = handler;
        this.context = context;
        this.mainController = mainController;
        this.mainActivity = mainActivity;
    }
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void run() {

        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI_RTT)) {
            System.out.println("running");
            Object service = mainActivity.getApplicationContext().getSystemService(Context.WIFI_RTT_RANGING_SERVICE);
            if (service instanceof WifiRttManager) {
                wifiRttManager = (WifiRttManager) service;
            }
            wifiManager = (WifiManager) mainActivity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            IntentFilter wifiFileter = new IntentFilter();

            wifiFileter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            wifiFileter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
            wifiFileter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
            mainActivity.registerReceiver(new WifiChangeReceiver(), wifiFileter);
        }
    }
//    private void startScanAPs() {
//        wifiManager.setWifiEnabled(true);
//        wifiManager.startScan();
//    }

    class WifiChangeReceiver extends BroadcastReceiver {
        @SuppressLint("MissingPermission")
        @RequiresApi(api = 28)
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                List<ScanResult> scanResults = wifiManager.getScanResults();
                    for(ScanResult scanResult: scanResults) {
                         RangingRequest.Builder builder = new RangingRequest.Builder();
                         builder.addAccessPoint(scanResult);
                         wifiRttManager.startRanging(builder.build(), mainController.getMyPool() , new RangingResultCallback() {
                            @Override
                            public void onRangingFailure(int i) {
                            }
                            @Override
                            public void onRangingResults(List<RangingResult> list) {
                                for(RangingResult result : list) {
                                    System.out.println(result);
                                }
                         }
                         });
                    }
            }
        }
    }
}