#undef min
#include <Seeed_Arduino_FreeRTOS.h>
#include <FreeRTOS.h>
#include <HTTPClient.h>


class SongDownloader{
  private:
    const String SONG_LIBRARY_URL = "http://192.168.0.135:8080";//"http://home4u-fa13b.web.app/info.json";
    const String SONG_INFO_PATH = SONG_LIBRARY_URL + "/info.json";
    const String SONGS_DIR_PATH = SONG_LIBRARY_URL + "/songs/"

    HTTPClient http;
    void (*songStreamCallback)());

    void startStreamingTask(){
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

    void doStreamTask(){
      this.songStreamCallback();
      http.disconnect();
    }

  public:

    SongDownloader(void (*songStreamCallback)()){
      this.songStreamCallback = songStreamCallback;
    }

    String getSongInfo(){
      myLog("Started downloading song info...");

      String output;
      this.http.begin(path);
      int resCode = http.GET();

      if(resCode == HTTP_CODE_OK){

        cost String songInfo = http.getString();
        myLog("Downloaded song info\n" + songInfo);
        output = songInfo;

      } else {

        myLog("Failed to download song info: " + String(resCode));
        output = NULL;

      }

      http.disconnect();
      return output;
    }

    void streamSong(String fileName){
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

        startStreamingTask();

        delay(3000);
        
      } else {
        myLog("Failed to download song: " + String(resCode));
      }
    }

    bool readSongSample(uint8_t* outputArray){
      if(! http.connected()) return false;

      int bytesRead = http.getStream().readBytes(outputArray, AUDIO_BUFFER_ENQUEUE_AMT);
      if(bytesRead == 0) return false;
    } 
}

