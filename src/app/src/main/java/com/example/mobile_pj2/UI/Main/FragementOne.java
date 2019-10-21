package com.example.mobile_pj2.UI.Main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.mobile_pj2.R;

public class FragementOne extends Fragment {
    private Context context;
    private ViewPager viewPager;
    public FragementOne(Context context){
        this.context = context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,container,false);
        viewPager = view.findViewById(R.id.act_viewPager);
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(context);
        viewPager.setAdapter(mainViewPagerAdapter);
        return view;
    }
}
