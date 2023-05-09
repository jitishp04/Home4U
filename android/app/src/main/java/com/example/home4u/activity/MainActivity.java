package com.example.home4u.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.home4u.NotificationHandler;
import com.example.home4u.R;
import com.example.home4u.alarm.AlarmStateConnection;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        goToAlarmActivityIfTriggered();
        NotificationHandler.handleNotificationPermission(this);

    }

    private void goToAlarmActivityIfTriggered(){
        AlarmStateConnection.isAlarmTriggered(isTriggered -> {
            if(isTriggered){
                final Intent newIntent = new Intent(this, AlarmActivity.class);
                startActivity(newIntent);
            }
        });
    }


}