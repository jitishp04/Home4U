package com.example.home4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.home4u.alarm.AlarmNotifierService;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        if(! AlarmNotifierService.isRunning()){
            final Intent newIntent = new Intent(this, AlarmNotifierService.class);
            this.startService(newIntent);
        }*/

        MusicInfoDownloader.download(new MusicInfoDownloaderCallback() {
             @Override
             public void onSuccess() {

             }

             @Override
             public void onFailure() {

             }
             }
        );
    }
}