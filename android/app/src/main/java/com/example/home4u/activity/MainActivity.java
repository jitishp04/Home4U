package com.example.home4u.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.home4u.NotificationHandler;
import com.example.home4u.R;
import com.example.home4u.alarm.AlarmStateConnection;
import com.example.home4u.connectivity.BrokerConnection;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private BrokerConnection brokerConnection;
    private Button enableAlarmBtn;
    private Switch securitySwitchBtn;
    private final int QOS = 0;
    private static final String SUB_TOPIC = "MotionDetector/Connection"; // topic to subscribe to
    public static final String PUB_TOPIC = "MotionDetector";
    private final static int MAX_MQTT_RETRY = 3;
    private String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        brokerConnection = BrokerConnection.getInstance(getApplicationContext());
        enableAlarmBtn = findViewById(R.id.enableAlarmButton);
        securitySwitchBtn = findViewById(R.id.securitySwitch);

        goToAlarmActivityIfTriggered();
        NotificationHandler.handleNotificationPermission(this);

        enableAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = "alarm";
                String secSwitch = enableAlarmBtn.getText().toString();
                publishCommand(message, 1);
                Toast.makeText(getApplicationContext(), "Alarm - " + secSwitch, Toast.LENGTH_SHORT).show();
            }
        });
        securitySwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                message = isChecked ? "enable" : "disable";
                String secSwitch = "";
                if (isChecked) {
                    secSwitch = securitySwitchBtn.getTextOn().toString();
                } else {
                    secSwitch = securitySwitchBtn.getTextOff().toString();
                }

                publishCommand(message, 1);

                Toast.makeText(getApplicationContext(), "Security system switch - " + secSwitch,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToAlarmActivityIfTriggered(){
        AlarmStateConnection.isAlarmTriggered(isTriggered -> {
            if(isTriggered){
                final Intent newIntent = new Intent(this, AlarmActivity.class);
                startActivity(newIntent);
            }
        });
    }

    private void publishCommand(String msg, int attemptNo){
        brokerConnection.getMqttClient(mqttClient -> {
            mqttClient.publish(PUB_TOPIC, message, QOS, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    exception.printStackTrace();

                    if(attemptNo <= MAX_MQTT_RETRY){
                        publishCommand(msg, attemptNo+1);
                    }
                }
            });
        });
    }


}