package com.example.mobile_pj2.UI.StudyHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.example.mobile_pj2.R;
import com.example.mobile_pj2.UI.Main.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class StudyHistoryActivity extends AppCompatActivity {

    private StudyHistoryAdapter studyHistoryAdapter = null;
    private Context mContext = null;
    private ListView list_main = null;
    private FileInputStream inputStream;
    private ImageView back_imageView;
    private ImageView delete_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyhistory);
        mContext = StudyHistoryActivity.this;
        bindViews();

        try {
            File file = new File(mContext.getExternalFilesDir("UserData"),"StudyRecord.txt");
            inputStream = new FileInputStream(file);
            Long filelength = file.length();
            byte[] filecontent = new byte[filelength.intValue()];
            inputStream.read(filecontent);
            String [] content = new String(filecontent).split("\n");
            ArrayList<String> studyHistory = new ArrayList<>();
            Collections.addAll(studyHistory,content);
            inputStream.close();

            System.out.println("file writing success");
            studyHistoryAdapter = new StudyHistoryAdapter(studyHistory,mContext);
            bindViews();
            list_main.setAdapter(studyHistoryAdapter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        back_imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(StudyHistoryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        delete_imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    File file1 = new File(mContext.getExternalFilesDir("UserData"), "StudyRecord.txt");
                    if (file1.exists()) {
                        boolean deleted = file1.delete();
                        studyHistoryAdapter.getStudyHistory().clear();
                        studyHistoryAdapter.update();
                        System.out.println("Successfully Delete");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindViews(){
        list_main = findViewById(R.id.list_history);
        back_imageView = findViewById(R.id.study_history_back);
        delete_imageView = findViewById(R.id.study_history_delete);
    }

}
