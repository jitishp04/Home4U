package com.example.home4u.alarm;

import static android.Manifest.permission.POST_NOTIFICATIONS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.home4u.R;
import com.example.home4u.activity.AlarmActivity;

public class AlarmNotificationHandler {
    private static final String CHANNEL_NAME = "Alarm notifications";
    private static final String CHANNEL_ID = "alarm_notification_channel";
    private static final int NOTIFICATION_ID = 3562988;
    private static final int CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;



    public static void createChannel(Context context){
        final NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_IMPORTANCE);

        final NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    public static boolean hasNotificationPermission(Context context){
        return
            android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU ||
            ContextCompat.checkSelfPermission(context, POST_NOTIFICATIONS) == PERMISSION_GRANTED;
    }


    static void post(Context context){
        if(!hasNotificationPermission(context)){
            return;
        }

        final Notification notification = create(context);
        final NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID, notification);
    }


    static Notification create(Context context){
        final Intent intent = new Intent(context, AlarmActivity.class);
        final PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentText("Alarm has been triggered")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.ic_alarm_notification)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);

        return builder.build();
    }

    static void cancel(Context context){
        final NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }

}
