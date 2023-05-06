package com.example.home4u.alarm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AlarmNotifierService extends Service {

    private static final String TAG = AlarmNotifierService.class.getSimpleName();


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

        watchAlarmIsTriggered();
    }

    private void watchAlarmIsTriggered(){
        final AlarmStateConnection watcher = AlarmStateConnection.getInstance();
        final Context context = this; //TODO: might cause errors

        watcher.watchAlarmIsTriggered(isTriggered -> {
            Log.v(TAG, "alarmIsTriggered: " + isTriggered);

            if(isTriggered) {
                AlarmNotification.post(context);
            } else {
                AlarmNotification.cancel(context);
            }
        });
    }
}
