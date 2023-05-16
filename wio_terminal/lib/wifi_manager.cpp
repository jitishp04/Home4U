#include <rpcWiFi.h>
#include "myEnv.h"
#include "TFT_eSPI.h"


void setupWifi() {
  Serial.print("Connecting to ");
  Serial.println(SSID);
  WiFi.begin(SSID, WIFI_PASSWORD); // Connecting WiFi

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
    WiFi.begin(SSID, WIFI_PASSWORD);
  }

  Serial.println("IP address: ");
  Serial.println(WiFi.localIP()); // Display Local IP Address
}