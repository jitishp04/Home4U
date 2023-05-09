package com.example.home4u;


import android.app.Application;

import com.example.home4u.alarm.AlarmStateWatcherHandler;

public class MyApp extends Application {
    public static final String spName = "preferences";

    @Override
    public void onCreate() {
        super.onCreate();

        NotificationHandler.handleNotificationChannels(this);

        AlarmStateWatcherHandler alarmStateWatcherHandler = AlarmStateWatcherHandler.getInstance(this);
        alarmStateWatcherHandler.start();
    }


}
