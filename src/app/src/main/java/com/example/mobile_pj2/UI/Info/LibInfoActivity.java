package com.example.mobile_pj2.UI.Info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mobile_pj2.R;
import com.example.mobile_pj2.UI.Main.MainActivity;

public class LibInfoActivity extends AppCompatActivity {
    private ImageView imageView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_info);
        imageView = findViewById(R.id.iv_btn_1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LibInfoActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        viewPager = findViewById(R.id.vp_1);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

    }
}
