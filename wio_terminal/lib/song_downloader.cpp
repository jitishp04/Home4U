#ifndef SONG_DOWNLOADER_H
#define SONG_DOWNLOADER_H


//#include <Seeed_Arduino_FreeRTOS.h>
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
    void (*songStreamCallback)();
    HTTPClient http;


  public:
    SongDownloader(void (*songStreamCallback)()){
      this->songStreamCallback = songStreamCallback;
    }

    String* getSongInfo(){
      myLog("Starting to download song info...");

      http.begin(SONG_INFO_PATH);
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

      http.begin(path);
      http.setTimeout(5000);
      const int resCode = http.GET();

      if(resCode == HTTP_CODE_OK){
        myLog("resCode 200");

        //Ignores header info of the .wav file
        for (int i = 0; i < 44; i++) {
          http.getStream().read();
        }

        streamHandler();
        
      } else {
        myLog("Failed to download song: " + String(resCode));
      }
    }

    //Starts the stream song method. Needs to be static to be able to be referenced propperly
    void streamHandler(){
      Stream& stream = http.getStream();

      this->songStreamCallback();
      myLog("Callback completed, closing connection...");
      http.end();
    }


    bool readSongSample(uint8_t* outputArray){
      if(! http.connected()) {
        return false;
      }

      int bytesRead = http.getStream().read();
      myLog("byte read: " + String(bytesRead));
      return true;
    } 
};





#endif