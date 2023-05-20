#include "lib/logger.cpp"
#include "lib/screen.cpp"
#include "lib/broker_conn.cpp"


const int PLAY_BTN = WIO_KEY_C;


SongInfo* playingSong;// = "scale.txt"; //Currently-playing song name
MusicPlayer* musicPlayer;
int songSelectedI = 0;


void setupMusicPlayer(){
  pinMode(PLAY_BTN, INPUT);

  pinMode(WIO_5S_UP, INPUT);
  pinMode(WIO_5S_DOWN, INPUT);
  pinMode(WIO_5S_PRESS, INPUT);

  musicPlayer = new MusicPlayer();
  playingSong = musicPlayer->getSongInfo(0);

  drawMusicPlayer();

  musicCallback = musicCommandListener;
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
  SongInfo* selectedSong = musicPlayer->getSongInfo(songSelectedI);

  tft.setTextColor(TFT_YELLOW);
  tft.setCursor((320 - tft.textWidth(selectedSong->getName())) / 2, 105); 
  tft.print(selectedSong->getName());
}

void drawPlayingSong(){
  tft.setTextColor(TFT_DARKCYAN);
  tft.setCursor((199 - tft.textWidth(playingSong->getName())) / 2, 199); 
  tft.print(playingSong->getName());
}



// ========= PROGRAM ========

void runMusicPlayer(){
  if (isPressed(PLAY_BTN)) {
    playingSong = musicPlayer->getSongInfo(songSelectedI);
    drawMusicPlayer();

    musicPlayer->playSong(playingSong->getFileName());
  } 
  
  //UP and DOWN Key for scrolling through the playlist
  else if (isPressed(WIO_5S_UP)) {
    songSelectedI = (songSelectedI -1 + musicPlayer->getSongAmt()) % musicPlayer->getSongAmt();
    drawMusicPlayer();
  } else if (isPressed(WIO_5S_DOWN)) {
    songSelectedI = (songSelectedI +1) % musicPlayer->getSongAmt();
    drawMusicPlayer();
  } else if(isPressed(WIO_5S_PRESS)){
    playingSong = musicPlayer->getSongInfo(songSelectedI);
    drawMusicPlayer();
  }

  runBrokerSub();


}

void musicCommandListener(String message){
  myLog("Music command: " + message);

  if(message == "pause"){

  } else {
    musicPlayer->playSong(message);
  }
}