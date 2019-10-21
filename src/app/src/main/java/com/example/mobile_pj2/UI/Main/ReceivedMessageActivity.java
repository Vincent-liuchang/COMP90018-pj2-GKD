package com.example.mobile_pj2.UI.Main;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pj2.R;

public class ReceivedMessageActivity extends AppCompatActivity {
    private TextView textView;
    private  String messageReceived = "Happiness is letting go for what you think your life is supposed to look like and celebrating it for everying that it is !";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.message_received);
        bindViews();
        textView.setText(messageReceived);
    }

    private void bindViews() {
        textView = findViewById(R.id.bottle_message_received);
    }
}
