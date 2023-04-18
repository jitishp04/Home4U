package com.example.home4u.alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.home4u.MainActivity;
import com.example.home4u.R;

public class AlarmNotification {
    public static final int NOTIFICATION_ID = 3562988;
    public static final String CHANNEL_NAME = "Alarm notifications";
    public static final String CHANNEL_ID = "alarm_notification_channel";


    public static void createChannel(Context context){
        final int importance = NotificationManager.IMPORTANCE_HIGH;
        final NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);

        final NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }


    public static void post(Context context){
        Notification notification = create(context);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private static Notification create(Context context){
        final Intent intent = new Intent(context, MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentText("Alarm has been triggered")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.ic_alarm_notification)
                .setContentIntent(pendingIntent);

        return builder.build();
    }

    public static void cancel(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }

}
