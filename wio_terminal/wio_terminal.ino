#include "TFT_eSPI.h"
#include "lib/song_downloader.cpp" //the file has to have a .cpp extension, not sure why
#include "lib/logger.cpp"
#include "lib/song_downloader_reader.cpp"
#undef read

TFT_eSPI tft;
SongDownloader songDownloader;

void setup()
{
  Serial.begin(9600);
  while(!Serial) ; //Wait until serial is open

  Serial.println();
  myLog("== Started ==");

  setupWifi();
  //setupMotion();
  //setupScreen();
  setupAudioPlayer();

  //songDownloader.getSongInfo();
  songDownloader.streamSong("bit.wav", songDownloadCallback);
}


void songDownloadCallback(SongDownloaderReader* songSampleReader){
  int sample;
  do{
    sample = songSampleReader->read();
    playSample(sample);
  } while(sample != -1);
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
  myLog("Looping");
  delay(5000);
  /*
  if(detectsMotion()){
    myLog("Motion detected");
    delay(500);
  }*/
}