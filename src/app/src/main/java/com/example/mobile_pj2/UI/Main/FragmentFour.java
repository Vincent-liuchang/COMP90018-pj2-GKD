package com.example.mobile_pj2.UI.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mobile_pj2.Control.MainController;
import com.example.mobile_pj2.R;

public class FragmentFour extends Fragment {
    private EditText editText;
    private TextView textView;
    private String myMessage;
    private ImageView writeMessage;
    private ImageView collectMessage;
    private MainController mainController;
    private  Context context;
    public FragmentFour(Context context, MainController mainController) {
        this.context = context;
        this.mainController = mainController;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, container, false);
        bindView(view);

        writeMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WriteMessage.class);
                startActivity(intent);
            }
        });

        collectMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ReceivedMessageActivity.class);
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