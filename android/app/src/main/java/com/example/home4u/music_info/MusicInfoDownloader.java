package com.example.home4u.music_info;

import com.example.home4u.server_manager.ServerHelper;
import com.example.home4u.server_manager.ServerRequestCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class MusicInfoDownloader {
    public static final String TAG = MusicInfoDownloader.class.getSimpleName();


    // *Inspired by ChatGPT*
    public static void download(MusicInfoDownloaderCallback callback) {
        ServerHelper.makeRequest("/info.json", new ServerRequestCallback() {
            @Override
            public void onMakeConnection(HttpURLConnection urlConnection) throws IOException {
                final InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                final String jsonString = ServerHelper.readStringStream(in);
                try {
                    final JSONObject res = new JSONObject(jsonString);
                    callback.onSuccess(res);
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure();
                }

            }

            @Override
            public void onConnectionError() {
                callback.onFailure();
            }
        });
    }

}
