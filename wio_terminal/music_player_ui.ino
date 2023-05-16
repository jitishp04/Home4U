String songName = "scale"; //Song name in the playlist
String curPlaySongName = "scale.txt"; //Currently-playing song name
bool showPopUpWin = false; //Flag for popup window
MusicPlayer* musicPlayer;


void setupMusicPlayer(){
    pinMode(WIO_KEY_C, INPUT);
    pinMode(WIO_KEY_B, INPUT);
    pinMode(WIO_5S_UP, INPUT);
    pinMode(WIO_5S_DOWN, INPUT);
    pinMode(WIO_5S_LEFT, INPUT);
    pinMode(WIO_5S_RIGHT, INPUT);
    pinMode(WIO_5S_PRESS, INPUT);

   musicPlayer = new MusicPlayer();
}

void runMusicPlayer(){
    drawMusicPlayer();

    //Show current playing song
    spr.setTextColor(TFT_DARKCYAN);
    spr.setCursor((199 - spr.textWidth(curPlaySongName)) / 2, 199); 
    spr.print(curPlaySongName);

    //Popup window for user confirmation (with two options: "Play"/"Cancel")
    if (showPopUpWin) {
        drawMusicControlPopup();
    }

    spr.pushSprite(0,0);

    //C Key for "Play" the current song
    if (digitalRead(WIO_KEY_C) == LOW) {
        spr.drawCircle(209,205,30,TFT_DARKCYAN);
        spr.fillCircle(209,205,30,TFT_DARKCYAN);
        spr.fillTriangle(199,190,199,220,224,205,TFT_YELLOW);
        spr.pushSprite(0,0);

        delay(1000);

        musicPlayer->playSong(curPlaySongName);
    } //UP and DOWN Key for scrolling through the playlist
    else if (digitalRead(WIO_5S_UP) == LOW) {
        songName = "scale.txt";
    } else if (digitalRead(WIO_5S_DOWN) == LOW) {
        songName = "twinkle_twinkle.txt";
    } //PRESS Key for openning up a popup window
    else if (digitalRead(WIO_5S_PRESS) == LOW) {
        showPopUpWin = true;
    }

    //Display a popup window
    if (showPopUpWin) {
        //LEFT Key for "Play" the selected song
        if (digitalRead(WIO_5S_LEFT) == LOW) {
            showPopUpWin = false;
            curPlaySongName = songName;        
            spr.fillEllipse(115,170,40,25,TFT_WHITE);
            spr.setTextColor(TFT_MAGENTA);
            spr.drawString("Play",92,165);
            spr.pushSprite(0,0);
            delay(1000);
        } //RIGHT Key for "Cancel" the selection
        else if (digitalRead(WIO_5S_RIGHT) == LOW) {
            showPopUpWin = false;    
            spr.fillEllipse(205,170,40,25,TFT_WHITE);
            spr.setTextColor(TFT_MAGENTA);
            spr.drawString("Cancel",170,165);
            spr.pushSprite(0,0);
            delay(1000);
        }
    }
}


void drawMusicPlayer(){
    spr.createSprite(TFT_HEIGHT, TFT_WIDTH);
    spr.fillSprite(TFT_DARKCYAN); //set background color

    //Draw title bar
    spr.setTextSize(2.5);
    spr.setTextColor(TFT_WHITE);
    spr.drawString("Media Player", 87, 10);
    spr.drawLine(0,40,320,40,TFT_WHITE);

    //Draw lower bar
    spr.drawRect(0,170,320,70, TFT_YELLOW);
    spr.fillRect(0,170,320,70, TFT_YELLOW);

    //Draw play icon
    spr.drawTriangle(199,190,199,220,224,205,TFT_DARKCYAN);
    spr.fillTriangle(199,190,199,220,224,205,TFT_DARKCYAN);

    //Draw pause icon
    spr.drawRect(260,188,8,35,TFT_DARKCYAN);
    spr.fillRect(260,188,8,35,TFT_DARKCYAN);
    spr.drawRect(276,188,8,35,TFT_DARKCYAN);
    spr.fillRect(276,188,8,35,TFT_DARKCYAN);

    //spr.drawRect(0,85,320,55, TFT_YELLOW);
    spr.drawTriangle(0,90,0,130,15,110,TFT_YELLOW);
    spr.fillTriangle(0,90,0,130,15,110,TFT_YELLOW);
    spr.drawTriangle(320,90,320,130,300,110,TFT_YELLOW);
    spr.fillTriangle(320,90,320,130,300,110,TFT_YELLOW);

    //Show scrolling play list
    spr.setTextColor(TFT_YELLOW);
    spr.setCursor((320 - spr.textWidth(songName)) / 2, 105); 
    spr.print(songName);
    spr.pushSprite(0,0);
}


void drawMusicControlPopup(){
    spr.drawRect(65,130,190,80,TFT_LIGHTGREY);
    spr.fillRect(65,130,190,80,TFT_LIGHTGREY);
    spr.drawEllipse(115,170,40,25,TFT_MAGENTA);
    spr.fillEllipse(115,170,40,25,TFT_MAGENTA);
    spr.drawEllipse(205,170,40,25,TFT_MAGENTA);
    spr.fillEllipse(205,170,40,25,TFT_MAGENTA);

    spr.setTextColor(TFT_WHITE);
    spr.drawString("Play",92,165);

    spr.setTextColor(TFT_WHITE);
    spr.drawString("Cancel",170,165);
    spr.pushSprite(0,0);
}