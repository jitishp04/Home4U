package com.example.home4u.connectivity;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerConnectionHelper {
    private static final String TAG = ServerConnectionHelper.class.getSimpleName();
    private static final String URL = "http://192.168.195.69:8081";


    // *Inspired by ChatGPT*
    public static void makeRequest(String endpoint, ServerRequestCallback callback){
        new Thread(() -> {
            HttpURLConnection urlConnection = null;
            try {
                final String path = ServerConnectionHelper.URL + endpoint;
                Log.v(TAG, "Making connection with " + path);
                final URL url = new URL(path);
                urlConnection = (HttpURLConnection) url.openConnection();

                callback.onMakeConnection(urlConnection);
            } catch (IOException e) {
                e.printStackTrace();
                callback.onConnectionError();
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
