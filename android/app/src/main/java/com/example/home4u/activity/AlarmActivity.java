package com.example.home4u.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.home4u.R;
import com.example.home4u.alarm.AlarmStateConnection;

public class AlarmActivity extends AppCompatActivity
{
    private static final String TAG = AlarmActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        AlarmStateConnection.isAlarmTriggered(isTriggered -> {
            if (isTriggered)
            {
                //Opening this activity means the user has acknowledged the alarm
                AlarmStateConnection.setAlarmIsTriggered(false);
            }
        });
    }
}
