package com.example.mobile_pj2.UI.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mobile_pj2.R;

public class FragmentThree extends Fragment {
    private String content;
    private TextView textView_building;
    private TextView textView_number;
    public FragmentThree(String content) {
        this.content = content;
    }
    public FragmentThree() {
        this.content = "no building";
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two,container,false);
        textView_building = view.findViewById(R.id.where);
        textView_building.setText(content);
        textView_number = view.findViewById(R.id.num_with_you);
        textView_number.setText("0");
        return view;
    }
}
