package com.example.mobile_pj2.UI.Main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.mobile_pj2.R;

public class MainViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    public MainViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.act_view_page_item,null);
        ImageView statistic_imageView = view.findViewById(R.id.act_image);

        String name = "mipmap/"+"act"+(position+1);
        statistic_imageView.setImageResource(this.getResource(name));
        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);

        final int cPos = position;
        statistic_imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ImageView i = (ImageView)v;
                if(cPos == 0)
                {
                    System.out.println("test click");
                    Uri uri = Uri.parse("https://library.unimelb.edu.au/news-and-events/horizon-lines-the-ambitions-of-a-print-collection");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);
                }else if (cPos == 1){
                    Uri uri = Uri.parse("https://library.unimelb.edu.au/news-and-events/have-your-say-about-our-libraries");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);
                }else if(cPos == 2){
                    Uri uri = Uri.parse("https://library.unimelb.edu.au/research/visualise-your-thesis");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);
                }
            }
        });

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