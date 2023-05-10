package com.example.home4u.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmStateWatcher extends BroadcastReceiver {
    private static final String TAG = AlarmStateWatcher.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmStateConnection.isAlarmTriggered(isTriggered -> {
            if(isTriggered) {
                AlarmNotificationHandler.post(context);
            } else {
                AlarmNotificationHandler.cancel(context);
            }
        });
    }
}