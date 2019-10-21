package com.example.mobile_pj2.UI.StudyHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mobile_pj2.R;

import java.util.ArrayList;
import java.util.HashMap;


public class StudyHistoryAdapter extends BaseAdapter {
    private ArrayList<String> studyHistory;
    private Context mContext;
    private TextView textView_Date = null;
    private TextView textView_Time = null;
    private TextView textView_Location =null;
    private HashMap hashMap = new HashMap();

    public StudyHistoryAdapter(ArrayList<String> studyHistory, Context mContext) {
        this.studyHistory = studyHistory;
        this.mContext = mContext;
    }

    public ArrayList<String> getStudyHistory(){
        return studyHistory;
    }

    @Override
    public int getCount() {
        return studyHistory.size();
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
        view = LayoutInflater.from(mContext).inflate(R.layout.studyhistory_item_list,viewGroup,false);
        textView_Date = view.findViewById(R.id.date_record);
        textView_Time = view.findViewById(R.id.time_record);
        textView_Location = view.findViewById(R.id.location_record);

        String str = studyHistory.get(i);
        str = str.substring(1,str.length()-1);
        String[] contents = str.split(", ");

        for (String s : contents) {
            String key=s.split("=")[0];
            String value=s.split("=")[1];
            hashMap.put(key, value);
        }
        String date = (String)hashMap.get("Date");
        date = date.split(" ")[1]+" "+ date.split(" ")[2];
        String hours = hashMap.get("hour")+" "+"hours"+" "+hashMap.get("minute")+" "+"minus";
        textView_Date.setText(date);
        textView_Time.setText(hours);
        textView_Location.setText((String)hashMap.get("currentBuilding"));
        return view;
    }

    public void update(){
        notifyDataSetChanged();
    }
}
