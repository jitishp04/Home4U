package com.example.home4u.music_info;

import com.example.home4u.ServerHelper;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MusicInfoDownloader {
    public static final String TAG = MusicInfoDownloader.class.getSimpleName();
    public static final String MUSIC_LIBRARY_URL = "https://home4u-fa13b.web.app/info.json";


    public static void downloadAsync(MusicInfoDownloaderCallback callback){
        new Thread(() -> {
            final JSONObject jsonObject = downloadSync();
            if(jsonObject == null) {
                callback.onFailure();
            } else {
                callback.onSuccess(jsonObject);
            }
        }).start();
    }

    // *Inspired by ChatGPT*
    private static JSONObject downloadSync(){
        HttpURLConnection urlConnection = null;
        try {
            final URL url = new URL(MUSIC_LIBRARY_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            final InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            final String jsonString = ServerHelper.readStringStream(in);
            return new JSONObject(jsonString);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }
    }


}
