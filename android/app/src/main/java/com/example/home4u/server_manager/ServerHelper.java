package com.example.home4u.server_manager;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerHelper {
    private static final String TAG = ServerHelper.class.getSimpleName();
    private static final String URL = "http://192.168.0.135:8081";


    public static void makeRequest(String endpoint, ServerRequestCallback requestCallback){
        new Thread(() -> {
            HttpURLConnection urlConnection = null;
            try {
                final String path = ServerHelper.URL + endpoint;
                Log.v(TAG, "Making connection with " + path);
                final URL url = new URL(path);
                urlConnection = (HttpURLConnection) url.openConnection();

                requestCallback.onMakeConnection(urlConnection);

            } catch (IOException e) {
                e.printStackTrace();
                requestCallback.onConnectionError();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            }).start();
    }


    // *Inspired by ChatGPT*
    public static String readStringStream(InputStream inputStream) throws IOException {
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
