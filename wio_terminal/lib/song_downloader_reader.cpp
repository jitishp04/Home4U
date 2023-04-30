#ifndef SONG_DOWNLOADER_READER_H
#define SONG_DOWNLOADER_READER_H

#undef min
#include <HTTPClient.h>


// Reads a stream provided by SongDownloader
class SongDownloaderReader{
  private:
    Stream& stream;

  public:
    static const int NO_MORE_DATA = -1;

    SongDownloaderReader(Stream& inputStream) : stream(inputStream) {}

    //returns -1 when there is no more data available
    int read(){
      return stream.read();
    }
};

#endif