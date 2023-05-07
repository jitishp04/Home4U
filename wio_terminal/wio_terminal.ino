#include "TFT_eSPI.h"
#include "lib/logger.cpp"
#include "lib/music_player.cpp"
#undef read

TFT_eSPI tft;
MusicPlayer* musicPlayer;

void setup()
{
  //do first!
  setupSerial();
  setupWifi();
  setupScreen();

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