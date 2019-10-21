package com.example.mobile_pj2.UI.Bottole;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pj2.R;

public class ReceivedMessageActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView_write_one;
    private ImageView imageView_go_back;
    private  String messageReceived = "Happiness is letting go for what you think your life is supposed to look like and celebrating it for everying that it is !";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        这里判断有没有新的消息
//        if()
//        setContentView(R.layout.message_received);
//        textView = findViewById(R.id.bottle_message_received);
//        textView.setText(messageReceived);

//        else
        setContentView(R.layout.no_message);
        imageView_write_one = findViewById(R.id.why_not_write_one);
        imageView_go_back = findViewById(R.id.bottle_nomessage_back);
        imageView_write_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReceivedMessageActivity.this,WriteMessage.class);
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