#undef min
#include <HTTPClient.h>

#define SET_ALARM_TRIGGERED_ENDPOINT "http://192.168.0.135:8081/setAlarmTriggered"


void notifyAlarm(){
  myLog("Sending request to: " + String(SET_ALARM_TRIGGERED_ENDPOINT));

  HTTPClient http;

  http.begin(SET_ALARM_TRIGGERED_ENDPOINT);

  http.addHeader("Content-Type", "text/plain");
  http.addHeader("Content-Length", "5");


  int resCode = http.PUT("true");
  if(resCode == HTTP_CODE_OK){
    myLog("Notify alarm success");
  } else {
    myLog("Notify alarm failure: " + String(resCode));
  }
}