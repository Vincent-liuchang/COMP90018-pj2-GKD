package com.example.mobile_pj2.UI.Openning;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pj2.R;

import java.util.Random;


public class SplashActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        linearLayout = findViewById(R.id.splash_layout);

        int randomInt = getRandomNumberInRange(1, 4);
        System.out.println("random int " + randomInt);

        switch (randomInt){
            case 1:
                linearLayout.setBackgroundResource(R.mipmap.splash_1_pic);
                break;
            case 2:
                linearLayout.setBackgroundResource(R.mipmap.splash_2_pic);
                break;
            case 3:
                linearLayout.setBackgroundResource(R.mipmap.splash_3_pic);
                break;
            case 4:
                linearLayout.setBackgroundResource(R.mipmap.splash_4_pic);
                break;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
                startActivity(i);
                finish();

            }
        }, 500);

    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
