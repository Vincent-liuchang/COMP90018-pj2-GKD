package com.example.mobile_pj2.UI.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mobile_pj2.R;

public class FragmentTwo extends Fragment {
    private String content;
    private TextView textView;
    public FragmentTwo(String content) {
        this.content = content;
    }
    public FragmentTwo() {
        this.content = "no building";
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two,container,false);
        textView = view.findViewById(R.id.where);
        textView.setText(content);
        return view;
    }
}
