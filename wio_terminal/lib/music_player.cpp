#ifndef MUSIC_PLAYER_H
#define MUSIC_PLAYER_H

#include "audio_player.cpp"
#include "song_downloader.cpp"

class MusicPlayer{
    public:
        MusicPlayer(){         
            //TODO: parse this file   
            String songInfo = songDownloader.downloadString("/info.json");
        }

        void playSong(String fileName){
            String song = songDownloader.downloadString("/songs/" + fileName);
        }

        static void onSongDownload(void* payload){
            MusicPlayer* instance = static_cast<MusicPlayer*>(payload);

            int sample;
            do{
                //sample = instance->songDownloader.read();
                instance->audioPlayer.playSample(sample);
            } while(sample != -1);
        }

    private:
        SongDownloader songDownloader;
        AudioPlayer audioPlayer;
};

#endif