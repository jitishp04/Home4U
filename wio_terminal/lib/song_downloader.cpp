#undef min
#include <Seeed_Arduino_FreeRTOS.h>
#include <FreeRTOS.h>
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

    void startStreamingTask(String path){
      TaskHandle_t streamSongHandle;

      //mylog("Starting streaming task...");

      StartStreamingTaskParams params = {
        path,
        this
      };

      xTaskCreate(
        startStreamSongTask, 
        "streamSong", 
        1024, 
        &params, 
        2, 
        &streamSongHandle
      );
    }

    static void startStreamSongTask(void* params){
      StartStreamingTaskParams* paramsStruct = static_cast<StartStreamingTaskParams*>(params);
      paramsStruct->instance->streamSong(paramsStruct->path);
    }

    void doStreamTask(){
      this->songStreamCallback();
      this->http.end();
    }

  public:
    const int AUDIO_BUFFER_ENQUEUE_AMT = 16384;

    SongDownloader(void (*songStreamCallback)()){
      this->songStreamCallback = songStreamCallback;
    }

    String* getSongInfo(){
      //mylog("Started downloading song info...");

      this->http.begin(SONG_INFO_PATH);
      int resCode = http.GET();

      if(resCode == HTTP_CODE_OK){

        String songInfo = http.getString();
        //mylog("Downloaded song info\n" + songInfo);

        http.end();
        return &songInfo;

      } else {

        //mylog("Failed to download song info: " + String(resCode));

        http.end();
        return nullptr;

      }
    }

    void streamSong(String fileName){
      //mylog("Started downloading song...");

      const String path = SONGS_DIR_PATH + fileName;

      http.begin(SONG_INFO_PATH);
      const int resCode = http.GET();

      if(resCode == HTTP_CODE_OK){
        //mylog("Downloaded song");

        //Ignores header info of the .wav file
        for (int i = 0; i < 44; i++) {
          http.getStream().read();
        }

        startStreamingTask(path);

        delay(3000);
        
      } else {
        //mylog("Failed to download song: " + String(resCode));
      }
    }

    bool readSongSample(uint8_t* outputArray){
      if(! http.connected()) return false;

      int bytesRead = http.getStream().readBytes(outputArray, AUDIO_BUFFER_ENQUEUE_AMT);
      if(bytesRead == 0) return false;
    } 
};