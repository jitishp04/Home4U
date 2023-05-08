package com.example.home4u.alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.home4u.R;

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


    static void post(Context context){
        final Notification notification = create(context);
        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID, notification);
    }


    static Notification create(Context context){
        final Intent intent = new Intent(context, AlarmActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

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
        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }

}
