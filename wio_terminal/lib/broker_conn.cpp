#ifndef BROKER_CONN_H
#define BROKER_CONN_H

#include <rpcWiFi.h>
#include <PubSubClient.h>

#define MQTT_PORT 1883
#define TOPIC_sub "Home4U/alarm"
#define TOPIC_pub_connection "MotionDetector/Connection"

WiFiClient wioClient;
PubSubClient client(wioClient);

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
      // Once connected, publish an announcement...
      client.publish(TOPIC_pub_connection, "hello world");
      Serial.println("Published connection message ");
      // ... and resubscribe
      client.subscribe(TOPIC_sub);
      Serial.print("Subcribed to: ");
      Serial.println(TOPIC_sub);
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" trying again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}


// =========== SUB =============

void callback(char* topic, byte* payload, unsigned int length) {

  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("] ");

  // process payload and convert it to a string
  char buff_p[length];
  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
    buff_p[i] = (char)payload[i];
  }
  Serial.println();
  buff_p[length] = '\0';
  String message = String(buff_p);

  setSecurityMode(message);
}

#endif