package com.example.home4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.home4u.alarm.AlarmStateConnection;
import com.example.home4u.alarm.AlarmStateListener;
import com.example.home4u.music_info.MusicInfoDownloader;
import com.example.home4u.music_info.MusicInfoDownloaderCallback;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toMusicSelectBtn = findViewById(R.id.button_to_music_select);
        toMusicSelectBtn.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, MusicSelectActivity.class))
        );

        AlarmStateConnection.getInstance().alarmIsTriggered(isTriggered -> {

        });

        /*
        if(! AlarmNotifierService.isRunning()){
            final Intent newIntent = new Intent(this, AlarmNotifierService.class);
            this.startService(newIntent);
        }*/


    }
}