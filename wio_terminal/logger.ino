
void myLog(String message){
  
  // *inspired by chatGpt*
  unsigned long currentMillis = millis();
  char timestamp[20];
  sprintf(timestamp, "[%02d:%02d:%02d.%03ld] ", 
    currentMillis / 3600000, 
    (currentMillis / 60000) % 60, 
    (currentMillis / 1000) % 60, 
    currentMillis % 1000
  );

  String output = timestamp + message;
  Serial.println(output);
}