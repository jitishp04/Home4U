#include "TFT_eSPI.h"
#include "lib/logger.cpp"
#include "lib/music_player.cpp"
#include "lib/wifi_manager.cpp"
#include "lib/screen.cpp"
#undef read


void setup()
{
  //Initial setup
  setupSerial();
  setupScreen();
  setupWifi();

  //Setup functions
  setupAlarm();
  setupMusicPlayer();
}


void setupSerial(){
  Serial.begin(9600);
  while(!Serial) ; //Wait until serial is open

  Serial.println();
  myLog("Setup serial");
}


void loop()
{
  runMusicPlayer();
  runAlarm();
}