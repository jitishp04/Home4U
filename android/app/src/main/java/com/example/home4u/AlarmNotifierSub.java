package com.example.home4u;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class AlarmNotifierSub implements IMqttActionListener, MqttCallback {
    public static final String MQTT_SERVER = "";
    public static final String CLIENT_ID = "";

    private static final String TAG = AlarmNotifierSub.class.getName();

    private final MqttClient mqttClient;

    public AlarmNotifierSub(Context context){
        mqttClient = new MqttClient(context, MQTT_SERVER, CLIENT_ID);
    }

    public void connect(){
        mqttClient.connect(CLIENT_ID, "", this, this);
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
    public void messageArrived(String topic, MqttMessage message) throws Exception {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
