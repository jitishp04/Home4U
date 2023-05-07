package com.example.home4u;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.home4u.alarm.AlarmNotification;
import com.example.home4u.alarm.AlarmServiceStarter;

public class MyApp extends Application {
    public static final String HAS_REGISTERED_NOTIFICATION_CHANNEL = "has registered notification channel";

    @Override
    public void onCreate() {
        super.onCreate();

        if(! hasRegisteredNotificationChannels()){
            registerNotificationChannels();
            setHasRegisteredNotificationChannels();
        }

        AlarmServiceStarter.start(this);
    }

    private boolean hasRegisteredNotificationChannels(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getBoolean(HAS_REGISTERED_NOTIFICATION_CHANNEL, false);
    }

    private void registerNotificationChannels(){
        AlarmNotification.createChannel(this);
    }

    private void setHasRegisteredNotificationChannels(){
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit()
                .putBoolean(HAS_REGISTERED_NOTIFICATION_CHANNEL, true)
                .apply();
    }
}
