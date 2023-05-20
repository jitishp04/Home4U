package com.example.home4u.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.home4u.scenes.music_screen_activity;
import com.example.home4u.scenes.scene_manager_screen.SceneManagerScreenActivity;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private BrokerConnection brokerConnection;
    private Button enableAlarmBtn, manageSceneBtn, playMusicBtn;
    private Switch securitySwitchBtn;
    private final int QOS = 0;
    private static final String SUB_TOPIC = "MotionDetector/Connection"; // topic to subscribe to
    public static final String PUB_TOPIC = "Home4U/alarm";
    private final static int MAX_MQTT_RETRY = 3;
    private String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        brokerConnection = BrokerConnection.getInstance(getApplicationContext());
        enableAlarmBtn = findViewById(R.id.enableAlarmButton);
        securitySwitchBtn = findViewById(R.id.securitySwitch);
        manageSceneBtn = findViewById(R.id.manageScenesButton);
        final Button goToMusicBtn = findViewById(R.id.playMusicButton);

        goToAlarmActivityIfTriggered();
        NotificationHandler.handleNotificationPermission(this);

        /**
         * Function that handles the event of turing on alarm manually while Security Mode is enabled
         */
        enableAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = "alarm";
                String secSwitch = enableAlarmBtn.getText().toString();
                publishCommandMsg(1);
                Toast.makeText(getApplicationContext(), "Alarm - " + secSwitch, Toast.LENGTH_SHORT).show();
            }
        });

        /**
         *  Function that handles the event of the securitySwitchBtn being checked or unchecked
         */
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

                publishCommandMsg(1);

                Toast.makeText(getApplicationContext(), "Security system switch - " + secSwitch,
                        Toast.LENGTH_SHORT).show();
            }
        });

        manageSceneBtn.setOnClickListener(view ->
                startActivity(new Intent(this, SceneManagerScreenActivity.class)));

        goToMusicBtn.setOnClickListener(view -> {
            final Intent newIntent = new Intent(this, MusicActivity.class);
            startActivity(newIntent);
        });
    }

    private void goToAlarmActivityIfTriggered(){
        AlarmStateConnection.isAlarmTriggered(isTriggered -> {
            if(isTriggered){
                final Intent newIntent = new Intent(this, AlarmTriggeredActivity.class);
                startActivity(newIntent);
            }
        });
    }

    private void publishCommandMsg(int attemptNo){
        brokerConnection.getMqttClient(mqttClient -> {
            Log.v(TAG, "Publishing: " + PUB_TOPIC + message);
            mqttClient.publish(PUB_TOPIC, message, QOS, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    exception.printStackTrace();

                    if(attemptNo <= MAX_MQTT_RETRY){
                        publishCommandMsg( attemptNo+1);
                    }
                }
            });
        });
    }


}