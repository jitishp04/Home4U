package com.example.home4u.connectivity;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/*
   Code based on DIT113MqttWorkshop
   Author: Nicole Quinstedt
   Source: https://github.com/Quinstedt/DIT113MqttWorkshop
*/

public class BrokerConnection{
    public static final String SUB_TOPIC = "MotionDetector/Connection"; // topic to subscribe to
    public static final String LOCALHOST = "10.0.2.2"; // Ip address of the local host
    private static final String MQTT_server = "tcp://" + LOCALHOST + ":1883"; // the server uses tcp protocol on the local host ip and listens to the port 1883
    public static final String CLIENT_ID = "DIT113-MotionDetector"; // the app client ID name
    public static final int QOS = 0; // quality of service

    private boolean isConnected = false;
    private final MqttClient mqttClient;
    private final Context context;

    //IMPORTANT! - PASSING ACTIVITY CONTEXT WILL CAUSE MEMORY LEAK
    public BrokerConnection(Context context) {
        this.context = context;
        mqttClient = new MqttClient(context, MQTT_server, CLIENT_ID);
        connectToMqttBroker();
    }

    public void connectToMqttBroker() {
        if (!isConnected) {
            mqttClient.connect(CLIENT_ID, "", new IMqttActionListener() {

                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    isConnected = true;
                    final String successfulConnection = "Connected to MQTT broker";
                    Log.i(CLIENT_ID, successfulConnection);
                    Toast.makeText(context, successfulConnection, Toast.LENGTH_LONG).show();
                    mqttClient.subscribe(SUB_TOPIC, QOS, null);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    final String failedConnection = "Failed to connect to MQTT broker";
                    Log.e(CLIENT_ID, failedConnection);
                    Toast.makeText(context, failedConnection, Toast.LENGTH_SHORT).show();
                }
            }, new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    isConnected = false;

                    final String connectionLost = "Connection to MQTT broker lost";
                    Log.w(CLIENT_ID, connectionLost);
                    Toast.makeText(context, connectionLost, Toast.LENGTH_SHORT).show();
                }

                /**
                 * Function that handles the messages received from the broker
                 *
                 * @param topic-  the topic that has been received
                 * @param message - the message received
                 * @throws Exception
                 */

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    if (topic.equals(SUB_TOPIC)) {
                        String messageMQTT = message.toString();
                        Log.i(CLIENT_ID, "Message " + messageMQTT);  // prints in the console
                    } else {
                        // prints in the console
                        Log.i(CLIENT_ID, "[MQTT] Topic: " + topic + " | Message: " + message.toString());
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.d(CLIENT_ID, "Message delivered");
                }
            });
        }
    }

    public MqttClient getMqttClient() {
        return mqttClient;
    }
}
