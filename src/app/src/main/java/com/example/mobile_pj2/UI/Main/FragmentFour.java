package com.example.mobile_pj2.UI.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.example.mobile_pj2.R;
import com.example.mobile_pj2.UI.Bottole.ReceivedMessageActivity;
import com.example.mobile_pj2.UI.Bottole.WriteMessage;

public class FragmentFour extends Fragment {
    private ImageView writeMessage;
    private ImageView collectMessage;
    private  Context context;
    private String buildingName;
    public FragmentFour(Context context, String buildingName) {
        this.context = context;
        this.buildingName = buildingName;
        System.out.println("FragmentFour"+buildingName);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_four, container, false);
        bindView(view);

        writeMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WriteMessage.class);
                intent.putExtra("currentBuilding",buildingName);
                startActivity(intent);
            }
        });

        collectMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReceivedMessageActivity.class);
                intent.putExtra("currentBuilding",buildingName);
                startActivity(intent);
            }
        });

        return view;
    }

    private void bindView(View view) {
        writeMessage = view.findViewById(R.id.bottle_write);
        collectMessage = view.findViewById(R.id.bottle_collect);
    }
}