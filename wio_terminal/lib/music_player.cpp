#ifndef MUSIC_PLAYER_H
#define MUSIC_PLAYER_H

#include "audio_player.cpp"
#include "song_downloader.cpp"
#include "song_downloader_reader.cpp"
#include "song_downloader_callback.cpp"

class MusicPlayer : public SongDownloadCallback{
    public:
        MusicPlayer(){         
            //TODO: parse this file   
            String* songInfo = songDownloader.getSongInfo();
        }

        void playSong(String fileName){
            songDownloader.streamSong(fileName, this);
        }

    private:
        SongDownloader songDownloader;
        AudioPlayer audioPlayer;

        void songDownloaded(SongDownloaderReader* songSampleReader) override{
            int sample;
            do{
                sample = songSampleReader->read();
                audioPlayer.playSample(sample);
            } while(sample != songSampleReader->NO_MORE_DATA);
        }
};

#endif