//#include "TFT_eSPI.h"
//#include "lib/audio_buffer.cpp"
#include "lib/song_downloader.cpp" //the file has to have a .cpp extension, not sure why
#include "lib/logger.cpp"
#include <HTTPClient.h>

//TFT_eSPI tft;
SongDownloader songDownloader(songDownloadCallback);
//AudioBuffer audioBuffer;

void setup()
{
  Serial.begin(9600);
  while(!Serial) ;

  myLog("== Started ==");

  setupWifi();
  //setupMotion();
  //setupScreen();
  //setupAudioPlayer();

  //songDownloader.getSongInfo();
  songDownloader.streamSong("test.wav");
  //playBuffer(audioBuffer);
}


void songDownloadCallback(){
  int sample;
  do{
    //uint8_t* enqueuePtr = audioBuffer.enqueuePtr();
    sample = songDownloader.readSongSample();
    playSample(sample);
    //myLog("Did download something: " + String(sample));
  } while(sample != -1);
}

void setupScreen(){
  // tft.begin();
  // tft.setRotation(3);

  // tft.fillScreen(TFT_BLACK);
  // tft.setTextColor(TFT_WHITE);
  // tft.setTextSize(3); 
}

void loop()
{
  myLog("Looping");
  delay(5000);
  /*
  if(detectsMotion()){
    myLog("Motion detected");
    delay(500);
  }*/
}