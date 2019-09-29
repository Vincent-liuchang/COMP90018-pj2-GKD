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
    public FragmentTwo(String content) {
        this.content = content;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two,container,false);
        TextView txt_content = (TextView) view.findViewById(R.id.two);
        txt_content.setText(content);
        return view;
    }
}
