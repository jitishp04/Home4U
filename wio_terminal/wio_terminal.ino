#include"TFT_eSPI.h"
TFT_eSPI tft;


void setup()
{
  Serial.begin(9600);
  myLog("== Started ==");

  setupWifi();
  //setupMotion();
  setupScreen();
  setupAudioPlayer();

  //downloadSongInfo();
  downloadSong("test.wav");
}

void setupScreen(){
  tft.begin();
  tft.setRotation(3);

  tft.fillScreen(TFT_BLACK);
  tft.setTextColor(TFT_WHITE);
  tft.setTextSize(3); 
}

void loop()
{
  if(detectsMotion()){
    myLog("Motion detected");
    delay(500);
  }
}