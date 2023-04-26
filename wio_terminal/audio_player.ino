
#define SPEAKER PIN_WIRE_SCL


void setupAudioPlayer()
{
    pinMode(SPEAKER,OUTPUT);
    digitalWrite(SPEAKER,LOW);
}

int lastInt = 0; //TMP!
void playSample(int input){
  //*Inspired by chatGPT*
  int16_t sample = lastInt | (input << 8);
  int pulseWidth = (sample + 32768) >> 8;

  //myLog("sample: " + String(sample) + " pW: " + String(pulseWidth));

  if(pulseWidth > 127){
    playSound(HIGH);
  } else {
    playSound(LOW);
  }

  lastInt = input;
}


void playSound(int value){
  digitalWrite(SPEAKER, value);
  delayMicroseconds(200);
}