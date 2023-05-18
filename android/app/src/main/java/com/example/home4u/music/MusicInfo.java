package com.example.home4u.music;

import com.example.home4u.connectivity.ServerConnectionHelper;
import com.example.home4u.connectivity.ServerRequestCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class MusicInfo {
    private final List<SongInfo> songs = new ArrayList<>();

    public List<SongInfo> getSongs() {
        return songs;
    }


    public void download(MusicInfoDownloaderCallback callback){
        ServerConnectionHelper.makeRequest("/info.json", new ServerRequestCallback() {
            @Override
            public void onMakeConnection(HttpURLConnection urlConnection) throws IOException {
                final InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                final String jsonString = ServerConnectionHelper.readStringStream(in);
                try {
                    parseDownload(jsonString);
                    callback.onDownloaded();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onConnectionError() {
                callback.onFailure();
            }
        });
    }


    private void parseDownload(String jsonString) throws JSONException {
        final JSONObject jsonObject = new JSONObject(jsonString);
        final JSONArray jsonSongs = jsonObject.getJSONArray("songs");

        final int songAmt = jsonSongs.length();
        for (int i = 0; i < songAmt; i++) {
            final JSONObject jsonSongInfo = jsonSongs.getJSONObject(i);

            final SongInfo songInfo = new SongInfo(
                    jsonSongInfo.getString("fileName"),
                    jsonSongInfo.getString("name"),
                    jsonSongInfo.getString("artist"),
                    jsonSongInfo.getString("album"),
                    jsonSongInfo.getString("length")
            );
            songs.add(songInfo);
        }
    }
}
