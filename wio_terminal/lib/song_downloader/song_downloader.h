
#pragma once
#include "song_downloader.cpp"


#undef min // needed for HTTPClient
#include <HTTPClient.h>

class SongDownloader{
    public:
        SongDownloader(void (*songStreamCallback)());
        String getSongInfo();
        void streamSong(String fileName);
        bool readSongSample(uint8_t* outputArray);

    private:
        const String SONG_LIBRARY_URL = "http://192.168.0.135:8080";//"http://home4u-fa13b.web.app/info.json";
        const String SONG_INFO_PATH = SONG_LIBRARY_URL + "/info.json";
        const String SONGS_DIR_PATH = SONG_LIBRARY_URL + "/songs/";

        HTTPClient http;
        void (*songStreamCallback)();

        void startStreamingTask();
        void doStreamTask();
};
