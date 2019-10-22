package com.example.mobile_pj2.UI.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mobile_pj2.R;
import com.example.mobile_pj2.UI.StudyHistory.StudyHistoryActivity;

public class FragmentThree extends Fragment {
    private String content;
    private Context context;
    private TextView textView_building;
    private TextView textView_number;
    private ImageView imageView_study_history;
    public FragmentThree(String content, Context context) {
        this.content = content;
        this.context = context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three,container,false);
        textView_building = view.findViewById(R.id.where);
        textView_building.setText(content);
        textView_number = view.findViewById(R.id.num_with_you);
        textView_number.setText("0");
        imageView_study_history = view.findViewById(R.id.my_state_study_history);

        imageView_study_history.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(context, StudyHistoryActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
