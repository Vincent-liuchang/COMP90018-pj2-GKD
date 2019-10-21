package com.example.mobile_pj2.UI.LibraryInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.mobile_pj2.R;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.mipmap.info_sta_1,R.mipmap.info_sta_2,
            R.mipmap.info_sta_3,R.mipmap.info_sta_4,R.mipmap.info_sta_5,
            R.mipmap.info_sta_6,R.mipmap.info_sta_7};

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
        View view = layoutInflater.inflate(R.layout.view_page_item,null);
        ImageView statistic_imageView = view.findViewById(R.id.info_statistic_image);

        String name = "mipmap/"+"info_sta_"+(position+1);
        statistic_imageView.setImageResource(this.getResource(name));
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

    public int getResource(String imageName) {
        int resId = context.getResources().getIdentifier(imageName, "mipmap", context.getPackageName());
        return resId;
    }
}