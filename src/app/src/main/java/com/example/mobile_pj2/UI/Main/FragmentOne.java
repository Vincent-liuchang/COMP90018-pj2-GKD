package com.example.mobile_pj2.UI.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.mobile_pj2.R;

public class FragmentOne extends Fragment {

    private BuildingAdapter buildingAdapter;
    private ListView list_main = null;
    private TextView textView =null;

    public FragmentOne(BuildingAdapter buildingAdapter) {
        this.buildingAdapter = buildingAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,container,false);
        list_main = view.findViewById(R.id.list_main);
        textView = view.findViewById(R.id.where);
        list_main.setAdapter(buildingAdapter);
        textView.setText("No Building");
        return view;
    }
}