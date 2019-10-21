package com.example.mobile_pj2.UI.Bottole;

import android.annotation.SuppressLint;
import android.app.Service;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pj2.Control.VibrationListener;
import com.example.mobile_pj2.R;
import com.example.mobile_pj2.UI.Main.MainActivity;

import java.util.concurrent.ThreadPoolExecutor;

public class WriteMessage extends AppCompatActivity {
    private EditText editText;
    private String myMessage;
    private ImageView throwMessage;
    private ImageView back;
    private ThreadPoolExecutor mypool;
    private Handler myHandler;
    private final int Vibrator = 4;
    private VibrationListener vibrationListener;

    private  String messageReceived = "Happiness is letting go for what you think your life is supposed to look like and celebrating it for everying that it is !";

    @SuppressLint("HandlerLeak")
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
        vibrationListener = new VibrationListener(myHandler,WriteMessage.this);
        mypool = MainActivity.mainController.getMyPool();
        mypool.execute(vibrationListener);
        setContentView(R.layout.write_message);
        bindViews();
        startWatchText(editText);
        setListeners();
    }



    private void bindViews() {
        editText = findViewById(R.id.bottle_message);
        throwMessage = findViewById(R.id.bottle_throw);
        back = findViewById(R.id.bottle_back);
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
        mypool.remove(vibrationListener);
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