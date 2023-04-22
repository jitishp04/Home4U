/****************************************************************************
  Code base on "MQTT Exmple for SeeedStudio Wio Terminal".
  Author: Salman Faris
  Source: https://www.hackster.io/Salmanfarisvp/mqtt-on-wio-terminal-4ea8f8
*****************************************************************************/
#define PIR_MOTION_SENSOR 0
#include <rpcWiFi.h>
#include"TFT_eSPI.h"
#include <PubSubClient.h>


const char* server = my_IPv4;  // MQTT Broker URL
const char* TOPIC_sub = sub_topic;
const char* TOPIC_pub_connection = pub_topic;

TFT_eSPI tft;
TFT_eSprite spr = TFT_eSprite(&tft);

PubSubClient client(wioClient);

String motionSensorMsg = ""; 
bool securityModeStateOn = false;
bool alarmOn = false;
bool alarmOffManually = false;


String getPayload(){

}

void callback(char* topic, byte* payload, unsigned int length) {
  tft.fillScreen(TFT_BLACK);
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
// end of conversion
  /***************  Action with topic and messages ***********/
  setSecurityMode(message);
}

void setSecurityMode(String message) {
  tft.fillScreen(TFT_BLACK);
  tft.setTextColor(TFT_WHITE, TFT_BLACK);
  tft.setTextSize(2);
  tft.setCursor((320 - tft.textWidth(message)) / 2, 120); 
  tft.print(message);

  if (message == "enable") {
    securityModeStateOn = true;
    alarmOffManually = false;
  } else {
    securityModeStateOn = false;
  }
}

void reconnect() {
  // Loop until we're reconnected
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    // Create a random client ID
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
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}

void setup() {
  tft.begin();
  tft.fillScreen(TFT_BLACK);
  tft.setRotation(3);

  pinMode(PIR_MOTION_SENSOR, INPUT);
  pinMode(WIO_BUZZER, OUTPUT);
  pinMode(WIO_5S_UP, INPUT);
  pinMode(WIO_5S_DOWN, INPUT);
  pinMode(WIO_5S_PRESS, INPUT);

  Serial.println();
  Serial.begin(115200);
  setup_wifi();
  client.setServer(server, 1883); // Connect the MQTT Server   hive_mqtt_server
  client.setCallback(callback);
}

void loop() {
  if (!client.connected()) {
    reconnect();
  }
  client.loop();

  if (!securityModeStateOn) {
      alarmOn = false;
      motionSensorMsg = "Security system disabled";
      analogWrite(WIO_BUZZER, 0);
      tft.fillScreen(TFT_BLACK);
      tft.setTextSize(2);
      tft.setCursor((320 - tft.textWidth(motionSensorMsg)) / 2, 120);
      tft.print(motionSensorMsg);
      Serial.println(motionSensorMsg);
      client.publish(TOPIC_pub_connection, "Security system disabled");
  }

  if (alarmOn) {
    analogWrite(WIO_BUZZER, 150);
    tft.fillScreen(TFT_BLACK);
    tft.setCursor((320 - tft.textWidth(motionSensorMsg)) / 2, 120); 
    tft.print(motionSensorMsg);

    // Use 5-Way Switch for turning off alarm mannually via Wio Terminal
    // Follow this pattern : UP -> DOWN -> PRESS
    if (digitalRead(WIO_5S_UP) == LOW) {
      tft.fillScreen(TFT_BLACK);
      tft.setCursor((320 - tft.textWidth("UP")) / 2, 120); 
      tft.print("UP");
      delay(1000);
      if (digitalRead(WIO_5S_DOWN) == LOW) {
        tft.fillScreen(TFT_BLACK);
        tft.setCursor((320 - tft.textWidth("DOWN")) / 2, 120); 
        tft.print("DOWN");
        delay(1000);
        if (digitalRead(WIO_5S_PRESS) == LOW) {
          tft.fillScreen(TFT_BLACK);
          tft.setCursor((320 - tft.textWidth("PRESS")) / 2, 120); 
          tft.print("PRESS");
          
          tft.fillScreen(TFT_BLACK);
          tft.setCursor((320 - tft.textWidth("Alarm turned off")) / 2, 120); 
          tft.print("Alarm turned off");
          analogWrite(WIO_BUZZER, 0);
          alarmOn = false;
          alarmOffManually = true;
          client.publish(TOPIC_pub_connection, "Alarm turned off mannually");
          delay(1000);
        }
      }
    }
  } else {
    if ((!alarmOffManually) && (securityModeStateOn)) {
      if (digitalRead(PIR_MOTION_SENSOR)) {
          alarmOn = true;
          motionSensorMsg = "Motion detected";
          tft.fillScreen(TFT_BLACK);
          client.publish(TOPIC_pub_connection, "Motion detected");
      } else {
          motionSensorMsg = "Watching";
          tft.setCursor((320 - tft.textWidth(motionSensorMsg)) / 2, 120);
          tft.print(motionSensorMsg);
          client.publish(TOPIC_pub_connection, "Watching");
      }
      Serial.println(motionSensorMsg);
      delay(1000);
    }
  }
  spr.pushSprite(0, 0);
  delay(2000);
}