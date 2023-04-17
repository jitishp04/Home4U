package com.example.home4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.home4u.music_info.MusicInfoDownloader;
import com.example.home4u.music_info.MusicInfoDownloaderCallback;
import com.example.home4u.scenes.SceneActivity;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button sceneMaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        if(! AlarmNotifierService.isRunning()){
            final Intent newIntent = new Intent(this, AlarmNotifierService.class);
            this.startService(newIntent);
        }*/

        MusicInfoDownloader.downloadAsync(new MusicInfoDownloaderCallback() {
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
            Intent sceneActivityIntent = new Intent(MainActivity.this, SceneActivity.class);
            startActivity(sceneActivityIntent);
        });

    }

    private void View() {
        sceneMaker = findViewById(R.id.sceneMaker);
    }
}