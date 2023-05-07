package com.example.home4u.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmStatusListener extends BroadcastReceiver {
    private static final String TAG = AlarmStatusListener.class.getSimpleName();


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "onRecieve");

        final AlarmStateConnection alarmStateConnection = AlarmStateConnection.getInstance();
        alarmStateConnection.alarmIsTriggered(isTriggered -> {
            Log.v(TAG, "alarmIsTriggered: " + isTriggered);

            if(isTriggered) {
                AlarmNotification.post(context);
            } else {
                AlarmNotification.cancel(context);
            }
        });
    }
}
