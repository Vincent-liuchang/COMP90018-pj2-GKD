package com.example.mobile_pj2.UI.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pj2.R;

public class ReceivedMessageActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;
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
       imageView = findViewById(R.id.why_not_write_one);
       imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReceivedMessageActivity.this,WriteMessage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
