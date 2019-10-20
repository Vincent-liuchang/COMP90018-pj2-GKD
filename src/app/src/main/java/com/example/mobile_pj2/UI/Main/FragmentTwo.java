package com.example.mobile_pj2.UI.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.example.mobile_pj2.R;
import com.example.mobile_pj2.UI.Info.LibInfoActivity;

public class FragmentTwo extends Fragment {

    private BuildingAdapter buildingAdapter;
    private ListView list_main = null;
    private Context context;

    public FragmentTwo(){}
    public FragmentTwo(BuildingAdapter buildingAdapter, Context context) {
        this.buildingAdapter = buildingAdapter;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,container,false);
        list_main = view.findViewById(R.id.list_main);
        list_main.setAdapter(buildingAdapter);
        return view;

    }


}