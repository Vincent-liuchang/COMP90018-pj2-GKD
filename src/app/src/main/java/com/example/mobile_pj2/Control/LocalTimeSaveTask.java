package com.example.mobile_pj2.Control;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

public class LocalTimeSaveTask implements Runnable {
    private String fileName;
    private String fileContent;
    private Context context;

    public LocalTimeSaveTask(Context context,String fileContent){
        this.fileName = "StudyRecord.txt";
        this.fileContent = fileContent;
        this.context = context;
    }

    @Override
    public void run() {
        FileOutputStream outputStream;
        try {
            File file = new File(context.getExternalFilesDir("UserData"),fileName);
            outputStream = new FileOutputStream(file,true);
            outputStream.write(fileContent.getBytes());
            outputStream.close();
            System.out.println("file writing success");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
