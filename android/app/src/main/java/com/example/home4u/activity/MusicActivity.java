package com.example.home4u.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import com.example.home4u.R;
import com.example.home4u.music.MusicInfo;
import com.example.home4u.music.MusicInfoDownloaderCallback;
import com.example.home4u.music.MusicPlayer;
import com.example.home4u.music.SongInfo;
import com.example.home4u.music.SongsListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MusicActivity extends AppCompatActivity {

    private MusicPlayer musicPlayer;
    private final MusicInfo musicInfo = new MusicInfo();
    private TextView song_count;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        musicPlayer = new MusicPlayer(musicInfo, this);
        handleSongs();

        song_count = findViewById(R.id.song_count);
        final FloatingActionButton pauseBtn = findViewById(R.id.pause_button);
        final ListView songListView = findViewById(R.id.song_list_view);

        songListView.setOnItemClickListener((adapterView, view, i, l) -> {
            musicPlayer.play(i);
        });

        pauseBtn.setOnClickListener(view -> musicPlayer.pause());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();  // Navigate back when the back arrow is clicked
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void handleSongs() {
        musicInfo.download(new MusicInfoDownloaderCallback() {
            @Override
            public void onDownloaded() {
                runOnUiThread(() -> {
                    song_count.setText(musicInfo.getSongs().size() + " songs");
                    populateSongView(musicInfo.getSongs());
                });
            }

            @Override
            public void onFailure() {

            }
        });
    }

    private void populateSongView(List<SongInfo> songs) {
        final ListView songListView = findViewById(R.id.song_list_view);
        final SongsListAdapter songListAdapter =
                new SongsListAdapter(MusicActivity.this, android.R.layout.simple_list_item_1, songs);
        songListView.setAdapter(songListAdapter);
    }

}

	
	