#ifndef SONG_DOWNLOADER_READER_H
#define SONG_DOWNLOADER_READER_H

#undef min //Needed for included HTTPClient
#include <HTTPClient.h>

class SongDownloaderReader{
  private:
    Stream& stream;
  public:
    SongDownloaderReader(Stream& inputStream) : stream(inputStream) {}

    //returns -1 when there is no more data available
    int read(){
      return stream.read();
    }
};

#endif