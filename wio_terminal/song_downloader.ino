#undef min
#undef Dacc
#undef Dac
#undef DAC
//#include <Audio.h>
#include <HTTPClient.h>
//#include <play_queue.h>
//#include <play_queue.cpp>
#define SAMPLE_RATE 16000
#define BLOCK_SIZE 65536

uint8_t buffer[BLOCK_SIZE];
const String SONG_LIBRARY_URL = "http://192.168.0.135:8080";//"http://home4u-fa13b.web.app/info.json";

void downloadSongInfo(){
  myLog("Started downloading song info...");

  const String path = SONG_LIBRARY_URL + "/info.json";

  HTTPClient http;
  http.begin(path);
  int resCode = http.GET();

  if(resCode == HTTP_CODE_OK){
    myLog("Downloaded song info");
    Serial.println(http.getString());
  } else {
    myLog("Failed to download song info: " + String(resCode));
  }
}

void downloadSong(String fileName){
  myLog("Started downloading song...");
  const String path = SONG_LIBRARY_URL + "/songs/" + fileName;
  //AudioPlayQueue mAudio;
  HTTPClient http;
  bool success = http.begin(path);
  int resCode = http.GET();

  if(resCode == HTTP_CODE_OK){
    myLog("Downloaded song");

    for (int i = 0; i < 44; i++) {
      http.getStream().read();
    }

    while (http.connected()) {
      int bytesRead = http.getStream().readBytes(buffer, BLOCK_SIZE);
      if (bytesRead <= 0) break;
      
      playBuffer(buffer, bytesRead);
    }
    myLog("The song has been played, are your ears still working?");
    
  } else {
    myLog("Failed to download song: " + String(resCode));
  }
}

