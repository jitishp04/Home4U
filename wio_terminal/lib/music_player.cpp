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

            pinMode(WIO_KEY_B, INPUT);
        }

        void playSong(String fileName){
            if(lastPlayedSong != fileName){
                songAudio = songDownloader.downloadString("/songs/" + fileName);

                this->lastPlayedSong = fileName;
                this->playerI = 0;
            }

            play();
        }


    private:
        int playerI = 0;
        String songAudio = "";
        String lastPlayedSong = "";
        SongDownloader songDownloader;

        void play(){
            int songStrLength = songAudio.length();
        
            for(; !isPausePressed(); playerI+=5){ 
                if(playerI >= songStrLength){
                    playerI = 0;
                    break;
                }
                
                String frequencyStr = songAudio.substring(playerI, playerI+4);
                if(frequencyStr == "0000"){
                    delay(MUSIC_TEMPO);
                } 
                else {
                    playTone(frequencyStr.toInt());
                }
            }
        }

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
            return digitalRead(WIO_KEY_B) == LOW;
        }
};

#endif