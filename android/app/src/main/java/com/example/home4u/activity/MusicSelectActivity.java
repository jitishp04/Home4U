package com.example.home4u.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.home4u.R;
import com.example.home4u.music.MusicInfo;
import com.example.home4u.music.MusicInfoDownloaderCallback;
import com.example.home4u.music.MusicPlayer;
import com.example.home4u.music.SongInfo;
import com.example.home4u.music.SongsListAdapter;

import java.util.List;

public class MusicSelectActivity extends AppCompatActivity {
    private static final String TAG = MusicSelectActivity.class.getSimpleName();

    private final MusicInfo musicInfo = new MusicInfo();
    private MusicPlayer musicPlayer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_select);

        musicPlayer = new MusicPlayer(musicInfo, this);
        handleSongs();
    }

    private void handleSongs(){
        musicInfo.download(new MusicInfoDownloaderCallback() {
            @Override
            public void onDownloaded() {
                runOnUiThread(() -> {
                    populateSongView(musicInfo.getSongs());
                });
            }

            @Override
            public void onFailure() {

            }
        });
    }

    private void populateSongView(List<SongInfo> songs){
        final ListView songListView = findViewById(R.id.song_list_view);
        final SongsListAdapter songListAdapter =
                new SongsListAdapter(MusicSelectActivity.this, android.R.layout.simple_list_item_1, songs);
        songListView.setAdapter(songListAdapter);
    }
}
