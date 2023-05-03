#include "TFT_eSPI.h"
//#include "myEnv.h"
#include <rpcWiFi.h>

#define M_SSID "testing"
#define PASSWORD ""


void setupWifi() {

  tft.setTextSize(2);
  tft.setCursor((320 - tft.textWidth("Connecting to Wi-Fi..")) / 2, 120);
  tft.print("Connecting to Wi-Fi..");

  Serial.print("Connecting to ");
  Serial.println(M_SSID);
  WiFi.begin(M_SSID, PASSWORD); // Connecting WiFi

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