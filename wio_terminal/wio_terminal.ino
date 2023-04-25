//#include "TFT_eSPI.h"
#include "lib/audio_buffer.cpp"
#include "lib/song_downloader.cpp" //the file has to have a .cpp extension, not sure why
#include "lib/logger.cpp"

//TFT_eSPI tft;
SongDownloader songDownloader(songDownloadCallback);
//AudioBuffer audioBuffer;

void setup()
{
  Serial.begin(9600);
  myLog("== Started ==");

  setupWifi();
  //setupMotion();
  //setupScreen();
  setupAudioPlayer();

  //songDownloader.getSongInfo();
  songDownloader.streamSong("test.wav");
  myLog("1");
  //playBuffer(audioBuffer);
  myLog("2");
}

void songDownloadCallback(){
  myLog("songDownloaderCallback");
  // bool couldDownload;
  // do{
  //   while(audioBuffer.isQueueFull()){
  //     myLog("Audio buffer is full");
  //     delay(300); //pauses both tasks?
  //   }
  //   uint8_t* enqueuePtr = audioBuffer.enqueuePtr();
  //   couldDownload = songDownloader.readSongSample(enqueuePtr);
  //   myLog("Downloaded song sample");
  // } while(couldDownload);
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