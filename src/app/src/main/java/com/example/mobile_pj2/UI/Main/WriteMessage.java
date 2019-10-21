package com.example.mobile_pj2.UI.Main;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pj2.Control.MainController;
import com.example.mobile_pj2.Control.VibrationHelper;
import com.example.mobile_pj2.R;

import java.util.concurrent.ThreadPoolExecutor;

public class WriteMessage extends AppCompatActivity {
    private EditText editText;
    private String myMessage;
    private ImageView throwMessage;
    private ImageView back;
    private ThreadPoolExecutor mypool;
    private Handler myHandler;
    private final int Vibrator = 4;
    private VibrationHelper vibrationHelper;

    private  String messageReceived = "Happiness is letting go for what you think your life is supposed to look like and celebrating it for everying that it is !";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myHandler = (new Handler(){
            public void handleMessage(Message message){
                switch(message.what){
                    case Vibrator:
                        Vibrate(500);

                }
            }
        });
        vibrationHelper = new VibrationHelper(myHandler,WriteMessage.this);

        mypool = MainActivity.getMainController().getMyPool();
        mypool.execute(vibrationHelper);

        setContentView(R.layout.write_message);
        bindViews();
        startWatchText(editText);

        setListeners();

    }



    private void bindViews() {
        editText = findViewById(R.id.bottle_message);
        throwMessage = findViewById(R.id.bottle_throw);
        back = findViewById(R.id.back);
    }

    private void setListeners() {
        throwMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrate(500);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    public void  startWatchText(EditText editText) {


        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myMessage = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        };
        editText.addTextChangedListener(watcher);
    }

    public void Vibrate(long milliseconds) {
        if (myMessage != null) {
            android.os.Vibrator vibrator = (android.os.Vibrator) this.getSystemService(Service.VIBRATOR_SERVICE);
            vibrator.vibrate(milliseconds);
            sendMessageToServer(myMessage);
            editText.setText(null);
            setMyMessage(null);

            finish();
        }

//            ImageView im = findViewById(R.id.bottle_shake);
//            im.startAnimation(rotateAnimation);
    }

    @Override
    protected void onDestroy() {
        mypool.remove(vibrationHelper);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void sendMessageToServer(String message) {

    }
}
