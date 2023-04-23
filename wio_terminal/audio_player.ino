/*macro definition of Speaker pin*/
#define SPEAKER PIN_WIRE_SCL
#define SAMPLE_RATE 16000

void setupAudioPlayer()
{
    pinMode(SPEAKER,OUTPUT);
    digitalWrite(SPEAKER,LOW);
}

int PWM_PERIOD = 62; 

void playBuffer2(uint8_t *data, int dataSize){
  for (int i = 0; i < dataSize; i++) {
    int sample = data[i];
    float dutyCycle = (sample + 32768) / 65535.0;
    int pulseWidth = dutyCycle * PWM_PERIOD;
    digitalWrite(SPEAKER, HIGH);
    delayMicroseconds(pulseWidth);
    digitalWrite(SPEAKER, LOW);
    delayMicroseconds(PWM_PERIOD - pulseWidth);
  }
}

void playBuffer(uint8_t *data, int dataSize){
  for (int i = 0; i < dataSize; i += 2) {
    int16_t sample = data[i] | (data[i + 1] << 8);
    int pulseWidth = (sample + 32768) >> 8;

    //myLog("sample: " + String(sample) + " pW: " + String(pulseWidth) + " dutyCycle: " + String(dutyCycle));

    for (int j = 0; j < SAMPLE_RATE / 50; j++) {
      if (pulseWidth > 127) {
        digitalWrite(SPEAKER, HIGH);
      } else {
        digitalWrite(SPEAKER, LOW);
      }
    }

  }
}

void playSound(int value){

}