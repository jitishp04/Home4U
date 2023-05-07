package com.example.home4u.alarm;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmServiceStarter {
    private static final String TAG = AlarmStateConnection.class.getSimpleName();

    public static void start(Context context){
        Log.v(TAG, "Starting alarm *service*");


        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        final Intent intent = new Intent(context, AlarmStatusListener.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        final long intervalMillis = 60 * 1000;
        final long firstMillis = System.currentTimeMillis() + 500;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, firstMillis, intervalMillis, pendingIntent);

    }
}
