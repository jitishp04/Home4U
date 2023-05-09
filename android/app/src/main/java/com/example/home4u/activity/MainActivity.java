package com.example.home4u.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.home4u.R;
import com.example.home4u.alarm.AlarmNotificationHandler;
import com.example.home4u.alarm.AlarmStateConnection;
import com.example.home4u.music_info.MusicInfoDownloader;
import com.example.home4u.music_info.MusicInfoDownloaderCallback;

import androidx.core.app.ActivityCompat;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        goToAlarmActivityIfTriggered();
        handleNotificationPermission();



        MusicInfoDownloader.download(new MusicInfoDownloaderCallback() {
             @Override
             public void onSuccess(JSONObject jsonObject) {

             }

             @Override
             public void onFailure() {
                 Log.e(TAG, "Failed to download music info");
             }
         });

    }

    private void goToAlarmActivityIfTriggered(){
        AlarmStateConnection.isAlarmTriggered(isTriggered -> {
            if(isTriggered){
                final Intent newIntent = new Intent(this, AlarmActivity.class);
                startActivity(newIntent);
            }
        });
    }

    private void handleNotificationPermission(){
        if(!AlarmNotificationHandler.hasNotificationPermission(this)){
            final String[] permissions = new String[]{
                Manifest.permission.POST_NOTIFICATIONS
            };
            ActivityCompat.requestPermissions(this, permissions, 0);
        }
    }
}