package com.example.home4u.connectivity;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

/*
   Code based on DIT113MqttWorkshop
   Author: Nicole Quinstedt
   Source: https://github.com/Quinstedt/DIT113MqttWorkshop
*/

public class BrokerConnection implements MqttCallback{
    private static final String TAG = BrokerConnection.class.getSimpleName();

    private static final String BROKER_HOST = "192.168.0.135"; // Ip address of the local host
    private static final String MQTT_server = "tcp://" + BROKER_HOST + ":1883"; // the server uses tcp protocol on the local host ip and listens to the port 1883
    private static final String CLIENT_ID = "DIT113-Home4U"; // the app client ID name
    private static final int BROKER_RECONNECT_DELAY = 10 * 1000;

    public static BrokerConnection instance;

    private final MqttClient mqttClient;
    private final Context context;
    private final List<BrokerConnectedCallback> connectedCallbacks = new ArrayList<>();

    private boolean isConnected = false;



    public static BrokerConnection getInstance(Context context) {
        if(instance == null){
            instance = new BrokerConnection(context);
        }
        return instance;
    }

    private BrokerConnection(Context context) {
        this.context = context.getApplicationContext();
        mqttClient = new MqttClient(this.context, MQTT_server, CLIENT_ID);
        connect();
    }


    private void connect() {
        mqttClient.connect(CLIENT_ID, "", new IMqttActionListener() {

            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                final String successfulConnection = "Connected to MQTT broker";
                Log.i(CLIENT_ID, successfulConnection);
                Toast.makeText(context, successfulConnection, Toast.LENGTH_LONG).show();

                onConnected();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                final String failedConnection = "Failed to connect to MQTT broker";
                Log.e(CLIENT_ID, failedConnection);
                Toast.makeText(context, failedConnection, Toast.LENGTH_SHORT).show();

                reconnect();
            }

        }, this);
    }

    private void reconnect(){
        new Handler().postDelayed(this::connect, BROKER_RECONNECT_DELAY);
    }

    private void onConnected(){
        isConnected = true;

        for(BrokerConnectedCallback connectedCallback: connectedCallbacks){
            connectedCallback.onConnected(mqttClient);
        }

        connectedCallbacks.clear();
    }



    @Override
    public void connectionLost(Throwable cause) {
        final String connectionLost = "Connection to MQTT broker lost";
        Log.w(CLIENT_ID, connectionLost);
        Toast.makeText(context, connectionLost, Toast.LENGTH_SHORT).show();

        isConnected = false;
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        Log.v(CLIENT_ID, "[MQTT] Topic: " + topic + " | Message: " + message.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.v(CLIENT_ID, "Message delivered");
    }


    public void getMqttClient(BrokerConnectedCallback callback) {
        if(isConnected){
            callback.onConnected(mqttClient);
        } else {
            connectedCallbacks.add(callback);
        }
    }
}
