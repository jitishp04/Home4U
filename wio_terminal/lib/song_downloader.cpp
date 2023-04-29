#ifndef SONG_DOWNLOADER_H
#define SONG_DOWNLOADER_H

#include "logger.cpp"
#include "song_downloader_reader.cpp"
#undef min //Needed for included HTTPClient
#include <HTTPClient.h>

#define SONG_INFO_PATH "http://192.168.205.69:8081/info.json" //"http://home4u-fa13b.web.app/info.json";
#define SONGS_DIR_PATH "http://192.168.205.69:8081/songs/"


void startStreamHandler(void* params);

class SongDownloader{

  public:
    SongDownloader(){}

    String* getSongInfo(){
      myLog("Downloading song info from: " + String(SONG_INFO_PATH));

      HTTPClient http;
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

    void streamSong(String fileName, void (*songStreamCallback)(SongDownloaderReader*)){
      const String path = SONGS_DIR_PATH + fileName;
      myLog("Started streaming " + path);

      //Makes a connection
      HTTPClient http;
      http.begin(path);
      http.setTimeout(5000);
      const int resCode = http.GET();

      if(resCode == HTTP_CODE_OK){
        myLog("Response: 200");
        Stream& stream = http.getStream();

        //Ignores header info of the .wav file, which is always 44 bytes long
        for (int i = 0; i < 44; i++) {
          stream.read();
        }

        //Calles callback
        SongDownloaderReader* reader = new SongDownloaderReader(stream);
        songStreamCallback(reader);

        //Closes connection and cleans up
        delete reader;
        http.end();

      } else {
        myLog("Failed to download song: " + String(resCode));
      }
    }
};





#endif