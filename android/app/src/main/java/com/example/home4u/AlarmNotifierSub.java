package com.example.home4u;

import android.content.Context;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class AlarmNotifierSub implements IMqttActionListener, MqttCallback {
    public static final String MQTT_SERVER = "";
    public static final String CLIENT_ID = "";

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

    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
