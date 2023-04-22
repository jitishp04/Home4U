#undef min
#include <HTTPClient.h>


const String SONG_LIBRARY_URL = "http://192.168.0.135:8080/info.json";//"http://home4u-fa13b.web.app/info.json";

void downloadSongInfo(){
  myLog("Started downloading...");

  HTTPClient http;
  http.begin(SONG_LIBRARY_URL);
  int resCode = http.GET();

  if(resCode == HTTP_CODE_OK){
    myLog("Downloaded song info");
    Serial.println(http.getString());
  } else {
    myLog("Failed to download song info: " + String(resCode));
  }
}

