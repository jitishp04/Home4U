#ifndef SONG_DOWNLOADER_H
#define SONG_DOWNLOADER_H


#include <Seeed_Arduino_FreeRTOS.h>
#include <FreeRTOS.h>
#include "logger.cpp"

#undef min //Needed for included HTTPClient
#include <HTTPClient.h>


class SongDownloader;


struct StartStreamingTaskParams {
  String path;
  SongDownloader* instance;
};


class SongDownloader{
  private:

    const String SONG_LIBRARY_URL = "http://192.168.0.135:8080";//"http://home4u-fa13b.web.app/info.json";
    const String SONG_INFO_PATH = SONG_LIBRARY_URL + "/info.json";
    const String SONGS_DIR_PATH = SONG_LIBRARY_URL + "/songs/";


    HTTPClient http;
    void (*songStreamCallback)();


    //Starts a background task to stream the song
    void startStreamingTask(String path){
      TaskHandle_t streamSongHandle;

      myLog("Starting streaming task...");

      StartStreamingTaskParams params = {
        path,
        this
      };

      xTaskCreate(
        startStreamSong, 
        "streamSong", 
        1024, 
        &params, 
        2, 
        &streamSongHandle
      );
    }

    //Starts the stream song method. Needs to be static to be able to be referenced propperly
    static void startStreamSong(void* params){
      StartStreamingTaskParams* paramsStruct = static_cast<StartStreamingTaskParams*>(params);
      paramsStruct->instance->streamSong(paramsStruct->path);
    }


  public:
    const int SAMPLE_SIZE = 16384; //How big audio sample should be downloaded each time

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
        myLog("Downloaded song");

        //Ignores header info of the .wav file
        for (int i = 0; i < 44; i++) {
          http.getStream().read();
        }

        startStreamingTask(path);

        delay(3000); //TMP!
        
      } else {
        myLog("Failed to download song: " + String(resCode));
      }
    }

    bool readSongSample(uint8_t* outputArray){
      if(! http.connected()) return false;

      int bytesRead = http.getStream().readBytes(outputArray, SAMPLE_SIZE);
      if(bytesRead == 0) return false;
    } 
};


#endif