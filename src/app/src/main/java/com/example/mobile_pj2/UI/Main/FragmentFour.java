package com.example.mobile_pj2.UI.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mobile_pj2.R;
import com.example.mobile_pj2.UI.ReceivedMessage.ReceivedMessageActivity;

public class FragmentFour extends Fragment {
    private EditText editText;
    private TextView textView;
    private String myMessage;
    private ImageView throwMessage;
    private  Context context;
    public FragmentFour(Context context) {
        this.context = context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_four,container,false);
        throwMessage = view.findViewById(R.id.bottle_throw);
        throwMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReceivedMessageActivity.class);
                startActivity(intent);
            }
        });
        startWatchText(view);
        return view;
    }

    public EditText getEditText() {
        return this.editText;
    }

    public String getMyMessage() {
        return this.myMessage;
    }

    public void setMyMessage(String message) {
        this.myMessage = message;
    }

    public void  startWatchText(View view) {
        editText = view.findViewById(R.id.bottle_message);
        System.out.println("startWatchText here");

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myMessage = s.toString();
                System.out.println("keyile xiongdi" + myMessage);
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        };
        editText.addTextChangedListener(watcher);
    }
}