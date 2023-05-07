#undef min
#include <HTTPClient.h>
#include "lib/myEnv.h"


bool notifyAlarm(){
  String path = String(SERVER_URL) + "/setAlarmTriggered";
  myLog("Sending request to: " + path);

  HTTPClient http;

  http.begin(path);

  http.addHeader("Content-Type", "text/plain");
  http.addHeader("Content-Length", "5");


  int resCode = http.PUT("true");
  if(resCode == HTTP_CODE_OK){
    myLog("Notify alarm success");
    return true;
  } else {
    myLog("Notify alarm failure: " + String(resCode));
    return false;
  }
}