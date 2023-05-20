#ifndef BROKER_CONN_H
#define BROKER_CONN_H

#include <rpcWiFi.h>
#include <PubSubClient.h>

#define MQTT_PORT 1883
#define TOPIC_SUB_ALARM "Home4U/alarm"
#define TOPIC_SUB_MUSIC "Home4U/music"
#define TOPIC_pub_connection "MotionDetector/Connection"

void callback(char* topic, byte* payload, unsigned int length);

const char* server = BROKER_IP;  // MQTT Broker ip, no protocol or port

WiFiClient wioClient;
PubSubClient client(wioClient);

void (*alarmCallback)(String) = nullptr;
void (*musicCallback)(String) = nullptr;


void setupBrokerConn(){
    client.setServer(server, MQTT_PORT); // Connect the MQTT Server
    client.setCallback(callback);
}


void connect() {
  // Loop until we're reconnected
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection with: tcp:// " + String(server) + " :" + MQTT_PORT + " ... ");
    String clientId = "WioTerminal";
    // Attempt to connect
    if (client.connect(clientId.c_str())) {
      Serial.println("connected");
      client.publish(TOPIC_pub_connection, "hello world");
      Serial.println("Published connection message ");

      // ... and resubscribe
      client.subscribe(TOPIC_SUB_ALARM);
      client.subscribe(TOPIC_SUB_MUSIC);

      Serial.println("Subcribed to: " + String(TOPIC_SUB_ALARM) + ", " + String(TOPIC_SUB_MUSIC));
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" trying again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}


void runBrokerSub(){
  if (!client.connected()) {
    connect();
  }
  client.loop();
}


// =========== SUB =============

void callback(char* topic, byte* payload, unsigned int length) {

  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("] ");
  String topicStr = String(topic);

  // process payload and convert it to a string
  char buff_p[length];
  for (int i = 0; i < length; i++) {
    buff_p[i] = (char)payload[i];
  }
  buff_p[length] = '\0';
  String message = String(buff_p);

  myLog("Recieved " + topicStr + " " + message);


  if(topicStr == String(TOPIC_SUB_ALARM) && alarmCallback != nullptr){
    alarmCallback(message);
  }

  if(topicStr == String(TOPIC_SUB_MUSIC) && musicCallback != nullptr){
    musicCallback(message);
  }
}

#endif