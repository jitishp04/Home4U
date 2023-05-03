#ifndef SONG_DOWNLOADER_CALLBACK_H
#define SONG_DOWNLOADER_CALLBACK_H

#include "song_downloader_reader.cpp"

// Pretty much an interface
class SongDownloadCallback {
    public:
        virtual void songDownloaded(SongDownloaderReader* songSampleReader) = 0;
        virtual ~SongDownloadCallback() {}
};

#endif