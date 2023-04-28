package com.example.home4u.alarm;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AlarmStateConnection {
    private static AlarmStateConnection instance;

    private static final String TAG = AlarmStateConnection.class.getSimpleName();
    private final DatabaseReference reference;

    public static AlarmStateConnection getInstance(){
        if(instance == null){
            instance = new AlarmStateConnection();
        }

        return instance;
    }

    private AlarmStateConnection(){
        final String ALARM_TRIGGERED_KEY = "alarmTriggered";
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        reference = database.getReference(ALARM_TRIGGERED_KEY);
    }

    public void watchAlarmIsTriggered(ValueEventListener listener){
        reference.addValueEventListener(listener);
    }

    public void alarmIsTriggered(ValueEventListener listener){
        reference.addListenerForSingleValueEvent(listener);
    }

    public void setAlarmIsTriggered(boolean value){
        reference.setValue(value);
    }
}
