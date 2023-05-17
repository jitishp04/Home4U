#ifndef MUSIC_PLAYER_H
#define MUSIC_PLAYER_H


#include "song_downloader.cpp"
#include <ArduinoJson.h>
#include "song_info.cpp"

#define SPEAKER PIN_WIRE_SCL

#define MUSIC_TEMPO 200

class MusicPlayer{
    public:
        MusicPlayer(){         
            //TODO: parse this file   
            String songInfo = songDownloader.downloadString("/info.json");

            parseSongInfo(songInfo);

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

        SongInfo* getSongInfo(int i){
            return songInfos[i];
        }

        int getSongAmt(){
            return songLen;
        }


    private:
        int playerI = 0;
        String songAudio = "";
        String lastPlayedSong = "";
        SongDownloader songDownloader;

        int songLen = 0;
        SongInfo** songInfos;


        void parseSongInfo(String jsonStr){
            DynamicJsonDocument doc(1024);
            deserializeJson(doc, jsonStr);


            songLen = doc["songAmt"];
            Serial.println(String(songLen));

            songInfos = new SongInfo*[songLen];


            for(int i = 0; i < songLen; i++){
                SongInfo* songInfo = new SongInfo(
                    doc["songs"][i]["name"],
                    doc["songs"][i]["fileName"]
                );

                songInfos[i] = songInfo;

                Serial.println(songInfo->getName());
            }
        }

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