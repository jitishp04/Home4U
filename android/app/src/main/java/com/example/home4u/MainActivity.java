package com.example.home4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

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

/*        Button toMusicSelectBtn = findViewById(R.id.button_to_music_select);
        toMusicSelectBtn.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, MusicSelectActivity.class))
        );*/

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

        /*View();

        sceneMaker.setOnClickListener(v -> {
            Intent sceneActivityIntent = new Intent(MainActivity.this, newscenecreator_activity.class);
            startActivity(sceneActivityIntent);
        });

    }

    private void View() {
<<<<<<< HEAD
<<<<<<< HEAD
        sceneMaker = findViewById(R.id.sceneMaker);

         */
=======
        //ceneMaker = findViewById(R.id.sceneMaker);
>>>>>>> 10b27dc (Fix layout screen.)
=======
        //SceneMaker = findViewById(R.id.sceneMaker);
>>>>>>> 98d19f8 (Code Clean Up.)
    }
}