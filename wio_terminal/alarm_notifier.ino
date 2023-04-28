#include <WiFiClientSecure.h>

#define FIREBASE_HOST "https://home4u-fa13b-default-rtdb.europe-west1.firebasedatabase.app/"
#define FIREBASE_PATH "alarmTriggered.json"

 // I can easily revoke this if I want to make it more secure or a problem arrieses, don't worry
#define FIREBASE_AUTH "uOWupBJNMrLWmgDU0rYJZED8GcNNovOLNipr1Tw5"


void notifyAlarm(){
  const String url = String(FIREBASE_HOST) + String(FIREBASE_PATH) + "?auth=" + String(FIREBASE_AUTH);

  WiFiClientSecure client;
  HTTPClient http;
  http.begin(client, SONG_INFO_PATH);

  int resCode = http.PUT("false");
  if(resCode == HTTP_CODE_OK){
    myLog("Notify alarm success");
  } else {
    myLog("Notify alarm failure");
  }
}