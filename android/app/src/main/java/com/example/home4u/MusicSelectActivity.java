package com.example.home4u;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.home4u.music_info.MusicInfoDownloader;
import com.example.home4u.music_info.MusicInfoDownloaderCallback;

import org.json.JSONObject;

public class MusicSelectActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_select);

        MusicInfoDownloader.downloadAsync(new MusicInfoDownloaderCallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                Log.v(TAG, jsonObject.toString());
            }

            @Override
            public void onFailure() {
                Log.e(TAG, "Failed to download music info");
            }
        });
    }
}
