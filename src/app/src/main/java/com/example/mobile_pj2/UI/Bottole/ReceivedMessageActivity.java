package com.example.mobile_pj2.UI.Bottole;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pj2.Control.CollectBottleMessageTask;
import com.example.mobile_pj2.Data.UpdateCallback;
import com.example.mobile_pj2.R;
import com.example.mobile_pj2.UI.Main.MainActivity;

import java.util.Map;

public class ReceivedMessageActivity extends AppCompatActivity {
    public TextView textView;
    private ImageView imageView_write_one;
    private ImageView imageView_go_back;
    private  String messageReceived = "Happiness is letting go for what you think your life is supposed to look like and celebrating it for everying that it is !";
    private  String currentBuilding;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        currentBuilding = intent.getStringExtra("currentBuilding");


        final CollectBottleMessageTask collectBottleMessageTask = new CollectBottleMessageTask(currentBuilding);
        collectBottleMessageTask.setUpdateCallback(new ReceiveCallBack() {
            public void update(Map messageReceived) {
                setContentView(R.layout.message_received);
                textView = findViewById(R.id.bottle_message_received);
                imageView_go_back = findViewById(R.id.arrow);
                textView.setText((String)messageReceived.get("content"));
                imageView_go_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        });

        MainActivity.mainController.getMyPool().execute(collectBottleMessageTask);

        setContentView(R.layout.no_message);
        imageView_write_one = findViewById(R.id.why_not_write_one);
        imageView_go_back = findViewById(R.id.bottle_nomessage_back);
        imageView_write_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReceivedMessageActivity.this, WriteMessage.class);
                startActivity(intent);
            }
        });

        imageView_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}