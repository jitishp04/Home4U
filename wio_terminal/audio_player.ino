/*macro definition of Speaker pin*/
#include "lib/audio_buffer.cpp"

#define SPEAKER PIN_WIRE_SCL
#define SAMPLE_RATE 16000


void setupAudioPlayer()
{
    pinMode(SPEAKER,OUTPUT);
    digitalWrite(SPEAKER,LOW);
}


void playBuffer(AudioBuffer audioBuffer){
  myLog("1.1");
  if(!hasIncoming(audioBuffer)){
    myLog("ERR: Buffer is empty!");
    return;
  }
  int int1 = audioBuffer.deque();
  int int2;

  while(hasIncoming(audioBuffer)){
    int int2 = audioBuffer.deque();

    //*Inspired by chatGPT
    int16_t sample = int1 | (int2 << 8);
    int pulseWidth = (sample + 32768) >> 8;

    myLog("sample: " + String(sample) + " pW: " + String(pulseWidth));

    if(pulseWidth > 127){
      playSound(HIGH);
    } else {
      playSound(LOW);
    }

    int1 = int2;
  }
}

bool hasIncoming(AudioBuffer audioBuffer){
  myLog("1.1.1");
  const int MAX_STREAM_DELAY = 5000;
  int waitedFor = 0;

  myLog("1.2");
  while(!audioBuffer.hasNext()){
    myLog("1.3");
    if(waitedFor >= MAX_STREAM_DELAY) return false;
    myLog("Waiting for audioBuffer");
    delay(1000);
    waitedFor += 1000;
  }

  return true;
}

void playSound(int value){
  digitalWrite(SPEAKER, value);
  delayMicroseconds(100);
}