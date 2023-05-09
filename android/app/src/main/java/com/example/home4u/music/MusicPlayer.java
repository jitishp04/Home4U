package com.example.home4u.music;

import android.content.Context;

import com.example.home4u.connectivity.BrokerConnection;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

public class MusicPlayer {
    private final static String TAG = MusicPlayer.class.getSimpleName();
    private final static String MQTT_TOPIC = "MusicPlayer";
    private final static int MQTT_QOS = 1;
    private final static int MAX_MQTT_RETRY = 3;

    private final MusicInfo musicInfo;
    private final BrokerConnection brokerConnection;

    public MusicPlayer(MusicInfo musicInfo, Context context){
        this.musicInfo = musicInfo;
        this.brokerConnection = BrokerConnection.getInstance(context);
    }

    public void play(SongInfo song){
        final String command = "play " + song.getFileName();
        publishCommand(command, 1);
    }

    public void pause(){

    }

    public void next(){

    }

    public void previous(){

    }

    private void publishCommand(String msg, int attemptNo){
        brokerConnection.getMqttClient(mqttClient -> mqttClient.publish(MQTT_TOPIC, msg, MQTT_QOS, new IMqttActionListener() {
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
        }));
    }
}
