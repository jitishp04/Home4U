package com.example.home4u;


import android.app.Application;
import android.util.Log;

import com.example.home4u.alarm.AlarmStateWatcherHandler;

public class MyApp extends Application {
    public static final String spName = "preferences";
    public static final String TAG = MyApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.v(TAG, "App started");

        NotificationHandler.registerNotificationChannels(this);

        AlarmStateWatcherHandler alarmStateWatcherHandler = AlarmStateWatcherHandler.getInstance(this);
        alarmStateWatcherHandler.start();
    }


}
