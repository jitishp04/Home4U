package com.example.home4u;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.home4u.alarm.AlarmNotification;

public class NotificationHelper {
    public static final String TAG = NotificationHelper.class.getName();


    @SuppressLint("MissingPermission")
    public static void postNotification(Notification notification, Context context){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        if (hasPermission(context)) {
            notificationManager.notify(AlarmNotification.NOTIFICATION_ID, notification);
        } else {
            Log.e(TAG, "Can't post notification, missing permission");
        }
    }

    public static boolean hasPermission(Context context){
        return ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED;
    }
}
