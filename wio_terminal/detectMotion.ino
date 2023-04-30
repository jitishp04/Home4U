/****************************************************************************
  Code base on "Grove - PIR Motion Sensor".
  Source: https://wiki.seeedstudio.com/Grove-PIR_Motion_Sensor/
*****************************************************************************//*macro definitions of PIR motion sensor pin and LED pin*/
#define PIR_MOTION_SENSOR PIN_WIRE_SCL // left


void setupMotion(){
  pinMode(PIR_MOTION_SENSOR, INPUT);
}

bool detectsMotion(){
  return digitalRead(PIR_MOTION_SENSOR);
}