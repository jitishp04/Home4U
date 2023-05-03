#ifndef AUDIO_PLAYER_H
#define AUDIO_PLAYER_H

#define SPEAKER PIN_WIRE_SCL

class AudioPlayer{
  public:
    AudioPlayer(){
      pinMode(SPEAKER, OUTPUT);
      digitalWrite(SPEAKER, LOW);
    }

    void playSample(int input){

      //*Inspired by chatGPT*
      int16_t sample = lastInt | (input << 8);
      int pulseWidth = (sample + 32768) >> 8;

      if(pulseWidth > 127){
        playSound(HIGH);
      } else {
        playSound(LOW);
      }

      lastInt = input;
    }
  
  private:
    int lastInt = 0;

    void playSound(int value){
      digitalWrite(SPEAKER, value);
      delayMicroseconds(200);
    }
};



#endif