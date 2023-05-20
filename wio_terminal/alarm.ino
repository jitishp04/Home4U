/****************************************************************************
  Code base on "MQTT Exmple for SeeedStudio Wio Terminal".
  Author: Salman Faris
  Source: https://www.hackster.io/Salmanfarisvp/mqtt-on-wio-terminal-4ea8f8
*****************************************************************************/

#include <rpcWiFi.h>
#include "TFT_eSPI.h"
#include "lib/broker_conn.cpp"

const char* server = BROKER_IP;  // MQTT Broker ip, no protocol or port
const int PIR_MOTION_SENSOR = PIN_WIRE1_SCL;


String motionSensorMsg = ""; 
bool securityModeStateOn = true; //default: false
bool alarmTriggered = false;
bool alarmOffManually = false;


// ========== SETUP ==========

void setupAlarm(){
  pinMode(PIR_MOTION_SENSOR, INPUT);
  pinMode(WIO_BUZZER, OUTPUT);
  pinMode(WIO_5S_UP, INPUT);
  pinMode(WIO_5S_DOWN, INPUT);
  pinMode(WIO_5S_PRESS, INPUT);
}


void setSecurityMode(String message) {
  tft.fillScreen(TFT_BLACK);
  tft.setTextColor(TFT_WHITE, TFT_BLACK);
  tft.setTextSize(2);
  tft.setCursor((320 - tft.textWidth(message)) / 2, 120); 
  tft.print(message);

  drawMusicPlayer();

  if (message == "enable") {
    securityModeStateOn = true;
    alarmOffManually = false;
  } else if (message == "alarm") {
    if (securityModeStateOn == true) {
      alarmTriggered = true; 
      Serial.println("Alarm on"); 
      motionSensorMsg = "Alarm turned on manually";
    }
  } else if (message == "disable") {
    securityModeStateOn = false;
  }
}



// =========== PROGRAM ===========

void runAlarm() {
  if (!client.connected()) {
    connect();
  }
  client.loop();

  if (!securityModeStateOn) {
      alarmTriggered = false;
      motionSensorMsg = "Security system disabled";
      analogWrite(WIO_BUZZER, 0);
      client.publish(TOPIC_pub_connection, "Security system disabled");
  }

  if (alarmTriggered) {
    alarmTriggeredProgram();
  } else {
    if ((!alarmOffManually) && (securityModeStateOn)) {
      if (digitalRead(PIR_MOTION_SENSOR)) {
        motionDetected();
      } else {
        client.publish(TOPIC_pub_connection, "Watching");
      }
      Serial.println(motionSensorMsg);
    }
  }
}



void alarmTriggeredProgram(){
  while(alarmTriggered){
    analogWrite(WIO_BUZZER, 150);
    tft.fillScreen(TFT_BLACK);
    tft.setCursor((320 - tft.textWidth(motionSensorMsg)) / 2, 120); 
    tft.print(motionSensorMsg);

    disableAlarmUi();
  }

  drawMusicPlayer();
}

void disableAlarmUi(){
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
        alarmTriggered = false;
        alarmOffManually = true;
        client.publish(TOPIC_pub_connection, "Alarm turned off mannually");
        delay(1000);
      }
    }
  }
}



void motionDetected(){
  alarmTriggered = true;
  motionSensorMsg = "Motion detected";
  client.publish(TOPIC_pub_connection, "Motion detected");

  bool success = notifyAlarm();
}