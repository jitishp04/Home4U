#ifndef SONG_DOWNLOADER_H
#define SONG_DOWNLOADER_H


#include <Seeed_Arduino_FreeRTOS.h>
//#include <FreeRTOS.h>
#include "logger.cpp"
#undef min //Needed for included HTTPClient
#include <HTTPClient.h>

#define SONG_INFO_PATH "http://192.168.0.135:8081/info.json" //"http://home4u-fa13b.web.app/info.json";
#define SONGS_DIR_PATH "http://192.168.0.135:8081/songs/"
#define SONG_SAMPLE_SIZE 1024 //needs to be the same in audio buffer


void startStreamHandler(void* params);

class SongDownloader{
  private:
    HTTPClient http;
    void (*songStreamCallback)();


    //Starts a background task to stream the song
    void startStreamingTask(String path){
      TaskHandle_t streamSongHandle;

      myLog("Starting streaming task 1...");

      BaseType_t status = xTaskCreate(
        startStreamHandler, 
        "streamSong", 
        2048, 
        this, 
        tskIDLE_PRIORITY + 10, 
        NULL
      );

      if(status != pdPASS) {
        myLog("ERR: Failed to create task!");
        return;
      }

      vTaskStartScheduler();
      myLog("f2");
    }



  public:
    SongDownloader(void (*songStreamCallback)()){
      this->songStreamCallback = songStreamCallback;
    }

    String* getSongInfo(){
      myLog("Starting to download song info...");

      this->http.begin(SONG_INFO_PATH);
      int resCode = http.GET();

      if(resCode == HTTP_CODE_OK){

        String songInfo = http.getString();
        myLog("Downloaded song info\n" + songInfo);

        http.end();
        return &songInfo;

      } else {

        myLog("Failed to download song info: " + String(resCode));

        http.end();
        return nullptr;

      }
    }

    void streamSong(String fileName){
      const String path = SONGS_DIR_PATH + fileName;

      myLog("Started downloading song...");

      http.begin(SONG_INFO_PATH);
      const int resCode = http.GET();

      if(resCode == HTTP_CODE_OK){
        myLog("resCode 200");

        //Ignores header info of the .wav file
        for (int i = 0; i < 44; i++) {
          http.getStream().read();
        }

        startStreamingTask(path);
        
      } else {
        myLog("Failed to download song: " + String(resCode));
      }
    }

    //This method is run in a different ~thread~
    void streamHandler(){
      songStreamCallback();
      this->http.end();
    }

    bool readSongSample(uint8_t* outputArray){
      if(! http.connected()) return false;

      int bytesRead = http.getStream().readBytes(outputArray, SONG_SAMPLE_SIZE);
      if(bytesRead == 0) return false;
      return true;
    } 
};


//Starts the stream song method. Needs to be static to be able to be referenced propperly
void startStreamHandler(void* params){
  myLog("Streaming task running");
  SongDownloader* songDownloader = static_cast<SongDownloader*>(params);
  songDownloader->streamHandler();
  vTaskDelete(NULL); //crashes without this
}


#endif