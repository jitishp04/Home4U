/*macro definition of Speaker pin*/
#define SPEAKER PIN_WIRE_SCL
#define SAMPLE_RATE 16000

void setupAudioPlayer()
{
    pinMode(SPEAKER,OUTPUT);
    digitalWrite(SPEAKER,LOW);
}


void playBuffer(void *input){//AudioBuffer audioBuffer){
  AudioBuffer audioBuffer = *(AudioBuffer*) input;
  int int1 = audioBuffer.deque();
  int int2;

  while(audioBuffer.hasNext()){
    int int2 = audioBuffer.deque();

    //*Inspired by chatGPT
    int16_t sample = int1 | (int2 << 8);
    int pulseWidth = (sample + 32768) >> 8;

    //myLog("sample: " + String(sample) + " pW: " + String(pulseWidth) + " dutyCycle: " + String(dutyCycle));

    if(pulseWidth > 127){
      playSound(HIGH);
    } else {
      playSound(LOW);
    }

    int1 = int2;
  }
}

void playSound(int value){
  digitalWrite(SPEAKER, value);
  delayMicroseconds(100);
}