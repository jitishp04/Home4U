/****************************************************************************
  Code base on "Grove - PIR Motion Sensor".
  Source: https://wiki.seeedstudio.com/Grove-PIR_Motion_Sensor/
*****************************************************************************//*macro definitions of PIR motion sensor pin and LED pin*/
#define PIR_MOTION_SENSOR PIN_WIRE_SCL
#include"TFT_eSPI.h"
TFT_eSPI tft;

void setup()
{
    pinMode(PIR_MOTION_SENSOR, INPUT);
    Serial.begin(9600);  

    tft.begin();
    tft.setRotation(3);
    
    tft.fillScreen(TFT_BLACK);
    tft.setTextColor(TFT_WHITE);
    tft.setTextSize(3);
}

void loop()
{
    if(digitalRead(PIR_MOTION_SENSOR)) {//if it detects the moving people?
        Serial.println("Motion detected");
        tft.drawString("Motion detected", 30, 100);
    }
    else {
        Serial.println("Watching");
        tft.fillScreen(TFT_BLACK);
        tft.drawString("", 30, 100);
    }
 delay(200);
}