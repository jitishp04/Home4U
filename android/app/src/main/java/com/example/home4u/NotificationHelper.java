package com.example.home4u;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper {

    @SuppressLint("MissingPermission")
    public static void postNotification(Notification notification, Context context){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        if (hasPermission(context)) {
            notificationManager.notify(AlarmNotification.NOTIFICATION_ID, notification);
        }
    }

    public static boolean hasPermission(Context context){
        return ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED;
    }
}
