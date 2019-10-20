package com.example.mobile_pj2.UI.Info;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mobile_pj2.Data.Model.Building;
import com.example.mobile_pj2.R;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.mipmap.info_sta_1,R.mipmap.info_sta_2,
            R.mipmap.info_sta_3,R.mipmap.info_sta_4,R.mipmap.info_sta_5,
            R.mipmap.info_sta_6,R.mipmap.info_sta_7};
    private String buildingName;


    public ViewPagerAdapter(String buildingName) {
        this.buildingName = buildingName;
//        switch (this.buildingName){
//            case "MSD":
//                this.images = {R.drawable.info_sta_MSD_1,R.drawable.info_sta_MSD_2,
//                               R.drawable.info_sta_MSD_3,R.drawable.info_sta_MSD_4,
//                               R.drawable.info_sta_MSD_5,R.drawable.info_sta_MSD_6,
//                               R.drawable.info_sta_MSD_7};
//                break;
//            case "GiblinEunsonLibrary":
//                this.images = {R.drawable.info_sta_Gib_1,R.drawable.info_sta_Gib_2,
//                        R.drawable.info_sta_Gib_3,R.drawable.info_sta_Gib_4,
//                        R.drawable.info_sta_Gib_5,R.drawable.info_sta_Gib_6,
//                        R.drawable.info_sta_Gib_7};
//                break;
//            case "LawLibrary":
//                this.images = {R.drawable.info_sta_Law_1,R.drawable.info_sta_Law_2,
//                        R.drawable.info_sta_Law_3,R.drawable.info_sta_Law_4,
//                        R.drawable.info_sta_Law_5,R.drawable.info_sta_Law_6,
//                        R.drawable.info_sta_Law_7};
//                break;
//            case"BaillieuLibrary":
//                this.images = {R.drawable.info_sta_Bai_1,R.drawable.info_sta_Bai_2,
//                        R.drawable.info_sta_Bai_3,R.drawable.info_sta_Bai_4,
//                        R.drawable.info_sta_Bai_5,R.drawable.info_sta_Bai_6,
//                        R.drawable.info_sta_Bai_7};
//                break;
//            case"ERC":
//                this.images = {R.drawable.info_sta_ERC_1,R.drawable.info_sta_ERC_2,
//                        R.drawable.info_sta_ERC_3,R.drawable.info_sta_ERC_4,
//                        R.drawable.info_sta_ERC_5,R.drawable.info_sta_ERC_6,
//                        R.drawable.info_sta_ERC_7};
//                break;
//            case"BiomedicalLibrary":
//                this.images = {R.drawable.info_sta_Bio_1,R.drawable.info_sta_Bio_2,
//                        R.drawable.info_sta_Bio_3,R.drawable.info_sta_Bio_4,
//                        R.drawable.info_sta_Bio_5,R.drawable.info_sta_Bio_6,
//                        R.drawable.info_sta_Bio_7};
//                break;
//        }
    }

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);
        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
