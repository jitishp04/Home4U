package com.example.home4u.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.home4u.R;
import com.example.home4u.music_info.MusicInfoDownloader;
import com.example.home4u.music_info.MusicInfoDownloaderCallback;
import com.example.home4u.music_info.MusicInfoParser;

import org.json.JSONObject;

public class MusicSelectActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_select);

        downloadSongs();
    }

    private void downloadSongs(){
        MusicInfoDownloader.download(new MusicInfoDownloaderCallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                String[] songs = MusicInfoParser.getSongs(jsonObject);
                runOnUiThread(() -> {
                    populateSongView(songs);
                });

                Log.v(TAG, jsonObject.toString());
            }

            @Override
            public void onFailure() {
                Log.e(TAG, "Failed to download music info");
            }
        });
    }

    private void populateSongView(String[] items){
        final ListView songListView = findViewById(R.id.song_list_view);
        final ArrayAdapter<String> songListAdapter = new ArrayAdapter<>(MusicSelectActivity.this, android.R.layout.simple_list_item_1, items);
        songListView.setAdapter(songListAdapter);
    }
}
