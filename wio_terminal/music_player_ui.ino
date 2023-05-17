#include "lib/logger.cpp"
#include "lib/screen.cpp"


const int PLAY_BTN = WIO_KEY_C;
const int PAUSE_BTN = WIO_KEY_B;


String selectedSong = "scale"; //Song name in the playlist
String playingSong = "scale.txt"; //Currently-playing song name
MusicPlayer* musicPlayer;


void setupMusicPlayer(){
  pinMode(PLAY_BTN, INPUT);
  pinMode(PAUSE_BTN, INPUT);

  pinMode(WIO_5S_UP, INPUT);
  pinMode(WIO_5S_DOWN, INPUT);
  pinMode(WIO_5S_PRESS, INPUT);

  musicPlayer = new MusicPlayer();
}


// ========= HELPERS =========

bool isPressed(int btn){
  return digitalRead(btn) == LOW;
}



// ========= DRAWING =========

void drawMusicPlayer(){
  drawMusicScreen();
  drawPlayingSong();
  drawSongInPlaylist();
}


void drawMusicScreen(){
  tft.fillScreen(TFT_DARKCYAN);

  //Draw title bar
  tft.setTextSize(2.5);
  tft.setTextColor(TFT_WHITE);
  tft.drawString("Media Player", 87, 10);
  tft.drawLine(0,40,320,40,TFT_WHITE);

  //Draw lower bar
  tft.drawRect(0,170,320,70, TFT_YELLOW);
  tft.fillRect(0,170,320,70, TFT_YELLOW);

  //Draw play icon
  tft.drawTriangle(199,190,199,220,224,205,TFT_DARKCYAN);
  tft.fillTriangle(199,190,199,220,224,205,TFT_DARKCYAN);

  //Draw pause icon
  tft.drawRect(260,188,8,35,TFT_DARKCYAN);
  tft.fillRect(260,188,8,35,TFT_DARKCYAN);
  tft.drawRect(276,188,8,35,TFT_DARKCYAN);
  tft.fillRect(276,188,8,35,TFT_DARKCYAN);

  //tft.drawRect(0,85,320,55, TFT_YELLOW);
  tft.drawTriangle(0,90,0,130,15,110,TFT_YELLOW);
  tft.fillTriangle(0,90,0,130,15,110,TFT_YELLOW);
  tft.drawTriangle(320,90,320,130,300,110,TFT_YELLOW);
  tft.fillTriangle(320,90,320,130,300,110,TFT_YELLOW);
}


void drawSongInPlaylist(){
  tft.setTextColor(TFT_YELLOW);
  tft.setCursor((320 - tft.textWidth(selectedSong)) / 2, 105); 
  tft.print(selectedSong);
}

void drawPlayingSong(){
  tft.setTextColor(TFT_DARKCYAN);
  tft.setCursor((199 - tft.textWidth(playingSong)) / 2, 199); 
  tft.print(playingSong);
}



// ========= PROGRAM ========

void runMusicPlayer(){
  if (isPressed(PLAY_BTN)) {
    playingSong = selectedSong;
    drawMusicPlayer();

    musicPlayer->playSong(playingSong);
  } 
  
  //UP and DOWN Key for scrolling through the playlist
  else if (isPressed(WIO_5S_UP)) {
    selectedSong = "scale.txt";
    drawMusicPlayer();
  } else if (isPressed(WIO_5S_DOWN)) {
    selectedSong = "twinkle_twinkle.txt";
    drawMusicPlayer();
  } else if(isPressed(WIO_5S_PRESS)){
    playingSong = selectedSong;
    drawMusicPlayer();
  }

}