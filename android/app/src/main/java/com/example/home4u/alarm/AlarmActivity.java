package com.example.home4u.alarm;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.home4u.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AlarmActivity extends AppCompatActivity {
    private static final String TAG = AppCompatActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        AlarmStateConnection alarmStateConnection = AlarmStateConnection.getInstance();
        alarmStateConnection.alarmIsTriggered(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final Boolean alarmIsTriggeredWrapped = snapshot.getValue(Boolean.class);
                if(Boolean.TRUE.equals(alarmIsTriggeredWrapped)){
                    alarmStateConnection.setAlarmIsTriggered(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Firebase canceled");
            }
        });
    }
}
