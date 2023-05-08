package com.example.home4u.alarm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.home4u.R;


public class AlarmActivity extends AppCompatActivity {
    private static final String TAG = AlarmActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        AlarmStateConnection.isAlarmTriggered(isTriggered -> {
            if (isTriggered) {
                AlarmStateConnection.setAlarmIsTriggered(false);
            }
        });
    }
}
