#include "TFT_eSPI.h"
#include "lib/audio_buffer/audio_buffer.h"
#include "lib/song_downloader.cpp" //the file has to have a .cpp extension, not sure why

TFT_eSPI tft;

void setup()
{
  Serial.begin(9600);
  myLog("== Started ==");

  setupWifi();
  //setupMotion();
  setupScreen();
  setupAudioPlayer();

  AudioBuffer audioBuffer;
  SongDownloader songDownloader(songDownloadCallback);
  songDownloader.getSongInfo();
  songDownloader.streamSong("test.wav");
}

void songDownloadCallback(){

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