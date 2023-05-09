package com.example.home4u.connectivity;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import info.mqtt.android.service.Ack;
import info.mqtt.android.service.MqttAndroidClient;


/*
   Code based on DIT113MqttWorkshop
   Author: Nicole Quinstedt
   Source: https://github.com/Quinstedt/DIT113MqttWorkshop
*/

 public class MqttClient {
    private static final String TAG = MqttClient.class.getSimpleName();
    private final MqttAndroidClient mMqttAndroidClient;

    MqttClient(Context context, String serverUrl, String clientId) {
        mMqttAndroidClient = new MqttAndroidClient(context, serverUrl, clientId, Ack.AUTO_ACK);
    }

    public void connect(String username, String password, IMqttActionListener connectionCallback, MqttCallback clientCallback) {

        mMqttAndroidClient.setCallback(clientCallback);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);

        mMqttAndroidClient.connect(options, null, connectionCallback);

    }

    // disconnect from mqtt broker
    public void disconnect(IMqttActionListener disconnectionCallback) {
        mMqttAndroidClient.disconnect(null, disconnectionCallback);
    }

    // receive message
    public void subscribe(String topic, int qos, IMqttActionListener subscriptionCallback) {
        mMqttAndroidClient.subscribe(topic, qos, null, subscriptionCallback);
    }

    // unsubscribe from a topic
    public void unsubscribe(String topic, IMqttActionListener unsubscriptionCallback) {
        mMqttAndroidClient.unsubscribe(topic, null, unsubscriptionCallback);
    }

    // send message
    public void publish(String topic, String message, int qos, IMqttActionListener publishCallback) {
        Log.v(TAG, "Publishing: " + topic + " " + message);
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(message.getBytes());
        mqttMessage.setQos(qos);

        mMqttAndroidClient.publish(topic, mqttMessage, null, publishCallback);

    }
}
