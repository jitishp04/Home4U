#ifndef MUSIC_PLAYER_H
#define MUSIC_PLAYER_H

#include "song_downloader.cpp"

#define SPEAKER PIN_WIRE_SCL


class MusicPlayer{
    public:
        MusicPlayer(){         
            //TODO: parse this file   
            String songInfo = songDownloader.downloadString("/info.json");
        }

        void playSong(String fileName){
            String song = songDownloader.downloadString("/songs/" + fileName);
            int songStrLength = song.length();

            for(int i = 0; i < songStrLength; i+=5){
                String frequencyStr = song.substring(i, i+4);
                playTone(frequencyStr.toInt());
            }
        }


    private:
        SongDownloader songDownloader;

        // Inspired by https://wiki.seeedstudio.com/Wio-Terminal-Buzzer/
        void playTone(int tone) {
            for (long i = 0; i < 300000; i += tone * 2) {
                digitalWrite(SPEAKER, HIGH);
                delayMicroseconds(tone);
                digitalWrite(SPEAKER, LOW);
                delayMicroseconds(tone);
            }
        }
};

#endif