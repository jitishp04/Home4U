package com.example.home4u.alarm;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AlarmNotifierWatcher {
    private final String TAG = AlarmNotifierWatcher.class.getSimpleName();
    private final DatabaseReference reference;

    public AlarmNotifierWatcher(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("alarmTriggered");
    }

    public void alarmIsTriggered(ValueEventListener listener){
        reference.addListenerForSingleValueEvent(listener);
    }
}
