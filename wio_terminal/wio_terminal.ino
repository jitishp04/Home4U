#include "TFT_eSPI.h"
#include "lib/audio_buffer.cpp"
#include "lib/song_downloader.cpp" //the file has to have a .cpp extension, not sure why
#include "lib/logger.cpp"

TFT_eSPI tft;
SongDownloader songDownloader(songDownloadCallback);
AudioBuffer audioBuffer;

void setup()
{
  Serial.begin(9600);
  myLog("== Started ==");

  setupWifi();
  //setupMotion();
  setupScreen();
  setupAudioPlayer();

  
  songDownloader.getSongInfo();
  songDownloader.streamSong("test.wav");
}

void songDownloadCallback(){
  uint8_t* enqueuePtr = audioBuffer.enqueuePtr();
  songDownloader.readSongSample(enqueuePtr);
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