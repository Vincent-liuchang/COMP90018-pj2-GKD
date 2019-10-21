package com.example.mobile_pj2.UI.LibraryInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mobile_pj2.R;
import com.example.mobile_pj2.UI.Main.MainActivity;

public class LibInfoActivity extends AppCompatActivity {
    private ImageView imageView_back;
    private ImageView imageView_intro;
    private ViewPager viewPager;
    private String buildingName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lib_info);
        Intent intent = getIntent();
        buildingName = intent.getStringExtra("buildingName");

        imageView_back = findViewById(R.id.info_back);
        imageView_intro = findViewById(R.id.info_lib_pic);
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LibInfoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        viewPager = findViewById(R.id.info_viewPager);
        InfoViewPagerAdapter infoViewPagerAdapter = new InfoViewPagerAdapter(this);
        viewPager.setAdapter(infoViewPagerAdapter);

        String name = "mipmap/"+"info_"+buildingName.toLowerCase();
        imageView_intro.setImageResource(this.getResource(name));
    }

    public int getResource(String imageName) {
        int resId = this.getResources().getIdentifier(imageName, "mipmap", this.getPackageName());
        return resId;
    }
}