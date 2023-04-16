package com.example.home4u;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class AlarmNotifierService extends Service {

    private static final String TAG = AlarmNotifierService.class.getName();
    private static boolean isRunning = false;
    private AlarmNotifierSub alarmNotifierSub;

    public static boolean isRunning(){
        return isRunning;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY; // The service will be restarted if it is killed
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.v(TAG, "Service started");

        assert !isRunning;
        isRunning = true;

        alarmNotifierSub = new AlarmNotifierSub(this);
        alarmNotifierSub.connect();
    }

    @Override
    public void onDestroy() { //TODO: this might not be called, find safer lifecycle methods
        super.onDestroy();

        alarmNotifierSub.disconnect();
        isRunning = false;
    }
}
