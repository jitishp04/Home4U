package com.example.home4u;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.home4u.alarm.AlarmStateWatcherHandler;

import java.util.Objects;

public class OnBootedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Objects.equals(intent.getAction(), Intent.ACTION_BOOT_COMPLETED)){
            startAlarmNotifierService(context);
        }
    }

    private void startAlarmNotifierService(Context context){
        final Application applicationContext = (Application) context.getApplicationContext();
        final AlarmStateWatcherHandler alarmStateWatcherHandler = AlarmStateWatcherHandler.getInstance(applicationContext);
        alarmStateWatcherHandler.start();
    }
}
