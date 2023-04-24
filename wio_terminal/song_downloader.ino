#undef min
#include <Seeed_Arduino_FreeRTOS.h>
#include <FreeRTOS.h>
#include <HTTPClient.h>


class HTTPClient;

class SongDownloader{
  private:
    const String SONG_LIBRARY_URL = "http://192.168.0.135:8080";//"http://home4u-fa13b.web.app/info.json";
    const String SONG_INFO_PATH = SONG_LIBRARY_URL + "/info.json";
    const String SONGS_DIR_PATH = SONG_LIBRARY_URL + "/songs/"

    HTTPClient http;

    void startStreamingSong(HTTPClient* httpClient){
      TaskHandle_t streamSongHandle;

      myLog("Starting streaming task...");

      xTaskCreate(
        streamSong, 
        "streamSong", 
        1024, 
        (void*) &input, 
        2, 
        &streamSongHandle
      );
    }

  public:

    SongDownloader(){

    }

    String getSongInfo(){
      myLog("Started downloading song info...");

      http.begin(path);
      int resCode = http.GET();

      if(resCode == HTTP_CODE_OK){

        cost String songInfo = http.getString();
        myLog("Downloaded song info\n" + songInfo);
        return songInfo;

      } else {

        myLog("Failed to download song info: " + String(resCode));
        return NULL;

      }
    }

    void downloadSong(String fileName, void (*func)()){
      myLog("Started downloading song...");

      const String path = SONGS_DIR_PATH + fileName;

      http.begin(path);
      const int resCode = http.GET();

      if(resCode == HTTP_CODE_OK){
        myLog("Downloaded song");

        //Ignores header info of the .wav file
        for (int i = 0; i < 44; i++) {
          http.getStream().read();
        }

        startStreamingSong(&http);

        delay(3000);
        playBuffer(&audioBuffer);
        
        myLog("The song has been played, are your ears still working?");
        
      } else {
        myLog("Failed to download song: " + String(resCode));
      }
    }

    bool readSongSample(uint8_t* outputArray){
      if(! http.connected()) return NULL;

      int bytesRead = http.getStream().readBytes(outputArray, AUDIO_BUFFER_ENQUEUE_AMT);
    } 
}

