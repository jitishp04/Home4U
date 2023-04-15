package com.example.home4u;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class AlarmNotifierSub implements IMqttActionListener, MqttCallback {
    private static final String TAG = AlarmNotifierSub.class.getName();

    public static final String MQTT_SERVER = "";
    public static final String CLIENT_ID = "";
    public static final String TOPIC = "";

    private final MqttClient mqttClient;
    private Context context;

    public AlarmNotifierSub(Context context) {
        mqttClient = new MqttClient(context, MQTT_SERVER, CLIENT_ID);
        this.context = context;
    }

    public void connect() {
        mqttClient.connect(CLIENT_ID, "", this, this);
        mqttClient.subscribe(TOPIC, 0, null);
    }

    public void disconnect() {
        mqttClient.disconnect(null);
        this.context = null;
    }


    @Override
    public void onSuccess(IMqttToken asyncActionToken) {

    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        Log.e(TAG, "Failed to connect to MQTT broker");
    }

    @Override
    public void connectionLost(Throwable cause) {
        Log.e(TAG, "Connection to MQTT broker lost");
    }


    @Override
    public void messageArrived(String topic, MqttMessage message) {
        if (message.toString().equals("motion_detected")) {
            Notification notification = AlarmNotification.create(context);
            NotificationHelper.postNotification(notification, context);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }


}
