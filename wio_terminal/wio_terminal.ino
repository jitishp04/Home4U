#include "TFT_eSPI.h"
#include "lib/logger.cpp"
#include "lib/music_player.cpp"
#include "lib/wifi_manager.cpp"
#undef read

TFT_eSPI tft;
TFT_eSprite spr = TFT_eSprite(&tft);
MusicPlayer* musicPlayer;

void setup()
{
  //do first!
  setupSerial();
  setupScreen();
  setupWifiWithUI();

  myLog("Initial setup done");
  delay(200);

  setupMotion();
  // musicPlayer = new MusicPlayer();
  // delay(4000);
  // musicPlayer->playSong("scale.txt");
  //notifyAlarm();
}


void setupSerial(){
  Serial.begin(9600);
  while(!Serial) ; //Wait until serial is open

  Serial.println();
  myLog("== Started ==");
}


void setupScreen(){
  tft.begin();
  tft.setRotation(3);

  tft.fillScreen(TFT_BLACK);
  tft.setTextColor(TFT_WHITE);
  tft.setTextSize(3); 
}

void setupWifiWithUI(){
  tft.setTextSize(2);
  tft.setCursor((320 - tft.textWidth("Connecting to Wi-Fi..")) / 2, 120);
  tft.print("Connecting to Wi-Fi..");

  setupWifi();

  Serial.println("WiFi connected");

  tft.fillScreen(TFT_BLACK);
  tft.setCursor((320 - tft.textWidth("Connected!")) / 2, 120);
  tft.print("Connected!");
}


void loop()
{
  if(detectsMotion()){
    myLog("Motion detected");
    bool success = notifyAlarm();
    if(success){
      delay(120000);
    } else {
      delay(1000);
    }
  } else {
    myLog("Watching...");
    delay(1000);
  }
}