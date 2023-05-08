package com.example.home4u;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.home4u.alarm.AlarmStateConnection;
import com.example.home4u.music_info.MusicInfoDownloader;
import com.example.home4u.music_info.MusicInfoDownloaderCallback;
import com.example.home4u.scenes.newscenecreator_activity;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button sceneMaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Button toMusicSelectBtn = findViewById(R.id.button_to_music_select);
        toMusicSelectBtn.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, AlarmActivity.class))
        );*/

        AlarmStateConnection.isAlarmTriggered(isTriggered -> {

        });

        /*
        if(! AlarmNotifierService.isRunning()){
            final Intent newIntent = new Intent(this, AlarmNotifierService.class);
            this.startService(newIntent);
        }*/

        MusicInfoDownloader.download(new MusicInfoDownloaderCallback() {
             @Override
             public void onSuccess(JSONObject jsonObject) {

             }

             @Override
             public void onFailure() {
                 Log.e(TAG, "Failed to download music info");
             }
         });

        View();

        sceneMaker.setOnClickListener(v -> {
            Intent sceneActivityIntent = new Intent(MainActivity.this, newscenecreator_activity.class);
            startActivity(sceneActivityIntent);
        });

    }

    private void View() {
        sceneMaker = findViewById(R.id.sceneMaker);
    }
}