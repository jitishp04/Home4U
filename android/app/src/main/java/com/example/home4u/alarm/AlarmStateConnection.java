package com.example.home4u.alarm;


import android.util.Log;

import com.example.home4u.server_manager.ServerHelper;
import com.example.home4u.server_manager.ServerRequestCallback;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class AlarmStateConnection {
    private static final String TAG = AlarmStateConnection.class.getSimpleName();

    private static AlarmStateConnection instance;


    public static AlarmStateConnection getInstance() {
        if (instance == null) {
            instance = new AlarmStateConnection();
        }

        return instance;
    }


    private AlarmStateConnection() {
    }


    public void isAlarmTriggered(AlarmStateConnListener listener) {
        ServerHelper.makeRequest("/isAlarmTriggered", new ServerRequestCallback() {
            @Override
            public void onMakeConnection(HttpURLConnection urlConnection) throws IOException {
                final InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                final String value = ServerHelper.readStringStream(in);
                Log.v(TAG, "isAlarmTriggered: " + value);

                final boolean isTriggered = value.equals("true");
                listener.onAlarmState(isTriggered);
            }

            @Override
            public void onConnectionError() {

            }
        });
    }


    public void setAlarmIsTriggered(boolean value) {
        ServerHelper.makeRequest("/setAlarmTriggered", new ServerRequestCallback() {
            @Override
            public void onMakeConnection(HttpURLConnection urlConnection) throws IOException {
                urlConnection.setRequestMethod("PUT");
                urlConnection.setRequestProperty("Content-Type", "text/plain");
                urlConnection.setDoOutput(true);

                final OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
                out.write(Boolean.toString(value));
                out.close();

                final int responseCode = urlConnection.getResponseCode();
                Log.v(TAG, "/setAlarmTriggered resCode: " + responseCode);
            }

            @Override
            public void onConnectionError() {

            }
        });
    }
}
