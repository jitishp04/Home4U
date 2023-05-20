package com.example.home4u;

import static android.Manifest.permission.POST_NOTIFICATIONS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.TIRAMISU;
import static androidx.core.content.ContextCompat.checkSelfPermission;
import static com.example.home4u.MyApp.spName;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.example.home4u.alarm.AlarmNotificationHandler;

public class NotificationHandler {
    public static final String TAG = NotificationHandler.class.getSimpleName();


    // =============== NOTIFICATION PERMISSION ================

    @SuppressLint("NewApi") // handled
    public static void handleNotificationPermission(Activity activity) {
        if (!hasNotificationPermission(activity)) {
            requestNotificationPermission(activity);
        }
    }

    public static boolean hasNotificationPermission(Context context) {
        if(SDK_INT < TIRAMISU) {
            return true;
        } else {
            return checkSelfPermission(context, POST_NOTIFICATIONS) == PERMISSION_GRANTED;
        }
    }

    @RequiresApi(api = TIRAMISU)
    private static void requestNotificationPermission(Activity activity) {
        final String[] permissions = new String[]{
                Manifest.permission.POST_NOTIFICATIONS
        };
        ActivityCompat.requestPermissions(activity, permissions, 0);
    }


    // ============ NOTIFICATION CHANNEL ===========

    //It is ok that this is run multiple times
    public static void registerNotificationChannels(Context context) {
        AlarmNotificationHandler.createChannel(context);
        Log.v(TAG, "Registered NotificationChannels");
    }

}
