#include "myEnv.h"
#include <rpcWiFi.h>

const char* ssid = M_SSID; // WiFi Name
const char* password = PASSWORD;  // WiFi Password


WiFiClient wioClient;


void setupWifi() {

  delay(400); // The connection is more reliable with this delay, for some reason

  //tft.setTextSize(2);
  //tft.setCursor((320 - tft.textWidth("Connecting to Wi-Fi..")) / 2, 120);
  //tft.print("Connecting to Wi-Fi..");

  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password); // Connecting WiFi

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");

  //tft.fillScreen(TFT_BLACK);
  //tft.setCursor((320 - tft.textWidth("Connected!")) / 2, 120);
  //tft.print("Connected!");

  Serial.println("IP address: ");
  Serial.println(WiFi.localIP()); // Display Local IP Address
}