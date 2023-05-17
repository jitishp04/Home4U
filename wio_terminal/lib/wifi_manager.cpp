#ifndef WIFI_MGR_H
#define WIFI_MGR_H

#include <rpcWiFi.h>
#include "myEnv.h"
#include "TFT_eSPI.h"
#include "screen.cpp"


void setupWifi() {
  Serial.print("Connecting to ");
  Serial.println(SSID);

  tft.setTextSize(2);
  tft.setCursor((320 - tft.textWidth("Connecting to Wi-Fi..")) / 2, 120);
  tft.print("Connecting to Wi-Fi..");

  WiFi.begin(SSID, WIFI_PASSWORD); // Connecting WiFi

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
    WiFi.begin(SSID, WIFI_PASSWORD);
  }

  Serial.println("WiFi connected, IP: " + String(WiFi.localIP()));

  tft.fillScreen(TFT_BLACK);
  tft.setCursor((320 - tft.textWidth("Connected!")) / 2, 120);
  tft.print("Connected!");

  delay(500);

  tft.fillScreen(TFT_BLACK);
}

#endif