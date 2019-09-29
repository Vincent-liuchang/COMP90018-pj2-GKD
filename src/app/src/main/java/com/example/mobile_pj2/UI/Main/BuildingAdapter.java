package com.example.mobile_pj2.UI.Main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobile_pj2.Data.Model.Building;
import com.example.mobile_pj2.R;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class BuildingAdapter extends BaseAdapter {

    private Context mContext;
    private CopyOnWriteArrayList<Building> buildingList;
    private Map<String,Integer> pics;

    public BuildingAdapter(CopyOnWriteArrayList<Building> buildingList, Context mContext) {
        this.buildingList = buildingList;
        this.mContext = mContext;
    }

    public void update(){
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return buildingList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.building_item_list,viewGroup,false);
        TextView textView1 = view.findViewById(R.id.building_name);
        TextView textView2 = view.findViewById(R.id.people_count);
        TextView textView3 = view.findViewById(R.id.distance);
        ImageView imageView = view.findViewById(R.id.building_pic);
        textView1.setText(buildingList.get(i).getBuildingName());
        textView2.setText(Integer.toString(buildingList.get(i).getPeopleInside()));
        textView3.setText(String.valueOf(buildingList.get(i).getDistance()));
        String name = "mipmap/"+buildingList.get(i).getBuildingName().toLowerCase();
        imageView.setImageResource(this.getResource(name));
        return view;
    }

    public int getResource(String imageName) {
        int resId = mContext.getResources().getIdentifier(imageName, "mipmap", mContext.getPackageName());
        System.out.println(mContext.getResources().getResourceName(R.mipmap.erc));
        return resId;
    }

}
