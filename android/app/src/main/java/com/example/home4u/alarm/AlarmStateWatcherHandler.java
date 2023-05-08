package com.example.home4u.alarm;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmStateWatcherHandler {
    private static final String TAG = AlarmStateWatcherHandler.class.getSimpleName();
    private static final int INTERVAL_MILLIS = 60 * 1000;

    private static AlarmStateWatcherHandler instance;

    private final PendingIntent pendingIntent;
    private final AlarmManager alarmManager;


    //Takes Application as a parameter because any other type of contexts can cause memory leaks
    public static AlarmStateWatcherHandler getInstance(Application context) {
        if (instance == null) {
            instance = new AlarmStateWatcherHandler(context);
        }
        return instance;
    }

    private AlarmStateWatcherHandler(Application context) {
        final Intent intent = new Intent(context, AlarmStateWatcher.class);
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
    }


    public void start() {
        final long firstMillis = System.currentTimeMillis() + 500;

        Log.v(TAG, "Starting AlarmManager to periodically check alarm state");
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, INTERVAL_MILLIS, pendingIntent);
    }

}
