#include "TFT_eSPI.h"
#include <rpcWiFi.h>
#include <stdlib.h>


const char* SSID = getenv("SSID");
const char* WIFI_PASSWORD = getenv("WIFI_PASSWORD");


void setupWifi() {

  tft.setTextSize(2);
  tft.setCursor((320 - tft.textWidth("Connecting to Wi-Fi..")) / 2, 120);
  tft.print("Connecting to Wi-Fi..");

  Serial.print("Connecting to ");
  Serial.println(SSID);
  WiFi.begin(SSID, WIFI_PASSWORD); // Connecting WiFi

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("WiFi connected");

  tft.fillScreen(TFT_BLACK);
  tft.setCursor((320 - tft.textWidth("Connected!")) / 2, 120);
  tft.print("Connected!");

  Serial.println("IP address: ");
  Serial.println(WiFi.localIP()); // Display Local IP Address
}