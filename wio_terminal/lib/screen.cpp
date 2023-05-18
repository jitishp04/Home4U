#ifndef SCREEN_H
#define SCREEN_H

#include "TFT_eSPI.h"


TFT_eSPI tft;


void setupScreen(){
  tft.begin();
  tft.setRotation(3);

  tft.fillScreen(TFT_BLACK);
  tft.setTextColor(TFT_WHITE);
  tft.setTextSize(3); 
}


#endif