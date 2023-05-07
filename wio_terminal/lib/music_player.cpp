#ifndef MUSIC_PLAYER_H
#define MUSIC_PLAYER_H

#include "audio_player.cpp"
#include "song_downloader.cpp"

class MusicPlayer{
    public:
        MusicPlayer(){         
            //TODO: parse this file   
            String* songInfo = songDownloader.getSongInfo();
        }

        void playSong(String fileName){
            songDownloader.streamSong(fileName, MusicPlayer::onSongDownload, this);
            myLog("Finished playing song");
        }

        static void onSongDownload(void* payload){
            MusicPlayer* instance = static_cast<MusicPlayer*>(payload);

            int sample;
            do{
                sample = instance->songDownloader.read();
                instance->audioPlayer.playSample(sample);
            } while(sample != instance->songDownloader.NO_MORE_DATA);
        }

    private:
        SongDownloader songDownloader;
        AudioPlayer audioPlayer;
};

#endif