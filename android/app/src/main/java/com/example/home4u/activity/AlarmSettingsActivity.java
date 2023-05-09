package com.example.home4u.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home4u.R;
import com.example.home4u.connectivity.BrokerConnection;


public class AlarmSettingsActivity extends AppCompatActivity {

    private Switch securitySwitchBtn; // The switch button for security system activation
    private Button alarmBtn; // The switch button for alarm activation
    private TextView modeState; // Used for the watching message
    private BrokerConnection brokerConnection; // Declare the brokerConnection
    int QOS = 0;
    public static final String PUB_TOPIC = "MotionDetector";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_settings);

        modeState = findViewById(R.id.ModeState);
        securitySwitchBtn = findViewById(R.id.switchSecurity);
        //alarmBtn = findViewById(R.id.btnAlarm);
        brokerConnection = new BrokerConnection(getApplicationContext());
        brokerConnection.connectToMqttBroker();

        /**
         *  Function that handles the event of the securitySwitchBtn being checked or unchecked
         */
        securitySwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String message = "";
                String secSwitch = "";
                if (isChecked) {
                    modeState.setText("Security mode is on");
                    message = "enable";
                    secSwitch = securitySwitchBtn.getTextOn().toString();
                } else {
                    modeState.setText("Security mode is off");
                    message = "disable";
                    secSwitch = securitySwitchBtn.getTextOff().toString();
                }
                brokerConnection.getMqttClient().publish(PUB_TOPIC, message, QOS, null);
                Toast.makeText(getApplicationContext(), "Security system switch - " + secSwitch,
                        Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * Function that handles the event of turing on alarm manually while Security Mode is enabled
         */
        alarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "alarm";
                String secSwitch = alarmBtn.getText().toString();
                brokerConnection.getMqttClient().publish(PUB_TOPIC, message, QOS, null);
                Toast.makeText(getApplicationContext(), "Alarm - " + secSwitch, Toast.LENGTH_SHORT).show();

            }
        });
    }
}