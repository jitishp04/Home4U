#ifndef SONG_DOWNLOADER_H
#define SONG_DOWNLOADER_H

#include "music_player.cpp"
#include "logger.cpp"
#undef min //Needed for included HTTPClient
#include <HTTPClient.h>
#include "myEnv.h"



void startStreamHandler(void* params);

class SongDownloader{
  public:
    SongDownloader(){}

    String downloadString(String endpoint){
      String path = SERVER_URL + endpoint;
      myLog("Downloading " + path);

      HTTPClient http;
      http.begin(path);
      int resCode = http.GET();

      if(resCode == HTTP_CODE_OK){
        String str = http.getString();
        myLog("Downloaded: " + str);

        http.end();
        return str;

      } else {

        myLog("Failed download: " + String(resCode));
        http.end();
        return "";

      }
    }


};



#endif