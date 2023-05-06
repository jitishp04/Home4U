package com.example.home4u.alarm;


import com.example.home4u.ServerHelper;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AlarmStateConnection {
    private static AlarmStateConnection instance;
    private static final String SERVER_URL = "http://192.168.0.135:8081";

    private static final String TAG = AlarmStateConnection.class.getSimpleName();

    public static AlarmStateConnection getInstance(){
        if(instance == null){
            instance = new AlarmStateConnection();
        }

        return instance;
    }

    private AlarmStateConnection(){

    }

    public void watchAlarmIsTriggered(AlarmStateListener listener){

    }

    public void alarmIsTriggered(AlarmStateListener listener){
        HttpURLConnection urlConnection = null;
        try {
            final URL url = new URL(SERVER_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            final InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            final String value = ServerHelper.readStringStream(in);

            listener.onAlarmStateChanged(value.equals("true"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }
    }

    public void setAlarmIsTriggered(boolean value){
        //reference.setValue(value);
    }
}
