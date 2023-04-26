#ifndef SONG_DOWNLOADER_H
#define SONG_DOWNLOADER_H


#include <Seeed_Arduino_FreeRTOS.h>
//#include <FreeRTOS.h>
#include "logger.cpp"
#undef min //Needed for included HTTPClient
#include <HTTPClient.h>

#define SONG_INFO_PATH "http://192.168.0.135:8080/info.json" //"http://home4u-fa13b.web.app/info.json";
#define SONGS_DIR_PATH "http://192.168.0.135:8080/songs/"
#define SONG_SAMPLE_SIZE 177 //needs to be the same in audio buffer


void startStreamHandler(void* params);

class SongDownloader{
  private:
    HTTPClient http;
    void (*songStreamCallback)();


    //Starts a background task to stream the song
    void startStreamingTask(String path){
      //TaskHandle_t streamSongHandle;

      myLog("Starting streaming task...");

      streamHandler(this);

      // BaseType_t status = xTaskCreate(
      //   SongDownloader::streamHandler, 
      //   "streamSong", 
      //   4096, 
      //   this, 
      //   tskIDLE_PRIORITY + 10, //won't start if too low
      //   NULL
      // );

      // if(status != pdPASS) {
      //   myLog("ERR: Failed to create task!");
      //   return;
      // }

      // vTaskStartScheduler();
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

      myLog("Started downloading " + path);

      http.begin(SONG_INFO_PATH);
      http.setTimeout(5000);
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

    //Starts the stream song method. Needs to be static to be able to be referenced propperly
    static void streamHandler(void* params){
      myLog("Streaming task running");
      SongDownloader* songDownloader = static_cast<SongDownloader*>(params);

      songDownloader->songStreamCallback();
      myLog("Callback completed, closing connection...");
      songDownloader->http.end();

      vTaskDelete(NULL); //crashes without this
    }


    bool readSongSample(uint8_t* outputArray){
      myLog("readSongSample");
      if(! http.connected()) {
        myLog("told you");
        return false;
      }
      myLog("j4");

      int bytesAvailable = http.getStream().available();
      while(bytesAvailable < SONG_SAMPLE_SIZE){
        myLog("not enough bytes: " + String(bytesAvailable));
        delay(300);
        bytesAvailable = http.getStream().available();
        myLog("No clue");
      }

      myLog("enough bytes: " + String(bytesAvailable));
      int bytesRead = http.getStream().read(outputArray, min(bytesAvailable, SONG_SAMPLE_SIZE));
      myLog("j5");
      return true;
    } 
};





#endif