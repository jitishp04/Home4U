#define PIR_MOTION_SENSOR PIN_WIRE_SCL
#include"TFT_eSPI.h"
TFT_eSPI tft;


void setup()
{
  pinMode(PIR_MOTION_SENSOR, INPUT);
  Serial.begin(9600); 

  setupScreen();
}

void setupScreen(){
  tft.begin();
  tft.setRotation(3);

  tft.fillScreen(TFT_BLACK);
  tft.setTextColor(TFT_WHITE);
  tft.setTextSize(3); 
}

void loop()
{
  Serial.println("running");

  //tft.drawString("running", 30, 100);
  runDetectMotion();
}