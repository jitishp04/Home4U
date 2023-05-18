#ifndef MUSIC_PLAYER_H
#define MUSIC_PLAYER_H


#include "song_downloader.cpp"

#define SPEAKER PIN_WIRE_SCL

#define MUSIC_TEMPO 200

class MusicPlayer{
    public:
        MusicPlayer(){         
            //TODO: parse this file   
            String songInfo = songDownloader.downloadString("/info.json");
        }

        void playSong(String fileName){
            song = songDownloader.downloadString("/songs/" + fileName);
            playerI = 0;
            play();
        }

        void play(){
            int songStrLength = song.length();
        
            for(int playerI = 0; playerI < songStrLength && !isPausePressed(); playerI+=5){ 
                String frequencyStr = song.substring(playerI, playerI+4);
                if(frequencyStr == "0000"){
                    delay(MUSIC_TEMPO);
                } 
                else {
                    playTone(frequencyStr.toInt());
                }
            }
        }


    private:
        int playerI = 0;
        String song = "";
        SongDownloader songDownloader;

        // Inspired by https://wiki.seeedstudio.com/Wio-Terminal-Buzzer/
        void playTone(int tone) {
            for (long i = 0; i < MUSIC_TEMPO * 1000; i += tone * 2) {
                digitalWrite(SPEAKER, HIGH);
                delayMicroseconds(tone);
                digitalWrite(SPEAKER, LOW);
                delayMicroseconds(tone);
            }
        }

        bool isPausePressed(){
            return WIO_KEY_B == LOW;
        }
};

#endif