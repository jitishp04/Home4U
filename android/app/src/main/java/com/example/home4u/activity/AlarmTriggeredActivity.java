package com.example.home4u.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.home4u.R;
import com.example.home4u.alarm.AlarmStateConnection;


public class AlarmTriggeredActivity extends AppCompatActivity {
    private static final String TAG = AlarmTriggeredActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_triggered);

        AlarmStateConnection.isAlarmTriggered(isTriggered -> {
            if (isTriggered) {

                //Opening this activity means the user has acknowledged the alarm
                AlarmStateConnection.setAlarmIsTriggered(false);
            }
        });

        final Button goToSettingsBtn = findViewById(R.id.alarm_triggered_go_to_settings);
        goToSettingsBtn.setOnClickListener((event) -> {
            final Intent newIntent = new Intent(this, MainActivity.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(newIntent);
            finish();
        });


    }
}
