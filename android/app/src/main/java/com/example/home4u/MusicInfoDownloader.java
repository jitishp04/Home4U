package com.example.home4u;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MusicInfoDownloader {
    public static final String MUSIC_LIBRARY_URL = "";

    // *Inspired by ChatGPT*
    public JSONObject download(){
        HttpURLConnection urlConnection = null;
        try {
            final URL url = new URL(MUSIC_LIBRARY_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            final InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            final String jsonString = readStream(in);
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

    // *Inspired by ChatGPT*
    private String readStream(InputStream inputStream) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final StringBuilder stringBuilder = new StringBuilder();

        String line = reader.readLine();
        while(line != null){
            stringBuilder.append(line);
            line = reader.readLine();
        }

        reader.close();
        return stringBuilder.toString();
    }
}
