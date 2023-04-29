#include <WiFiClientSecure.h>

#define FIREBASE_HOST "https://home4u-fa13b-default-rtdb.europe-west1.firebasedatabase.app/"
#define FIREBASE_PATH "alarmTriggered.json"

 // I can easily revoke this if I want to make it more secure or a problem arrieses, don't worry
#define FIREBASE_AUTH "uOWupBJNMrLWmgDU0rYJZED8GcNNovOLNipr1Tw5"


void notifyAlarm(){
  String url = String(FIREBASE_HOST) + String(FIREBASE_PATH) + "?auth=" + String(FIREBASE_AUTH);
  myLog("Sending request to: " + url);

  WiFiClientSecure client;
  myLog("3");
  client.setTimeout(4);
  myLog("3.5");
  client.setHandshakeTimeout(5);
  client.setCACert("-----BEGIN CERTIFICATE-----\nMIIC/DCCAeSgAwIBAgIIdKoGffvgKmYwDQYJKoZIhvcNAQEFBQAwIDEeMBwGA1UE\nAxMVMTAzMzM5ODYwODMwNDA4MDkxNDY3MCAXDTIzMDQyODA1MDM0NVoYDzk5OTkx\nMjMxMjM1OTU5WjAgMR4wHAYDVQQDExUxMDMzMzk4NjA4MzA0MDgwOTE0NjcwggEi\nMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDNfFCDNaEFIAEHkNfasA7KODXJ\n4draUNf4MnVZXq7JXw/ZTdqHoFaR9vIJVHPftoXWrbAAzOpzlqFl6bXftmvybi3S\nf0fYqISQTsdBQF9qy/Jp+X9YvceGLEEpNpM14s7ZMBilKfLbLyOA8efSLyMoDjVj\nGVJNZAx3z5uYD5TJlXwvBL+qj/FEcAzhA1Iyi7HEgv2FZQN5Gt9ZxgkGgKWUQP4Y\ndROgGX2WBMxZOlmJ2FtHda8K9Nfjz/G6tG5uRlMFmYLiLIjxI1AGHM9mqSN2RCyh\nltSxK7jJ/gcDuxuq771c43gmMZLPTBCypVGdJUcRu9tSTr84JxSwFfhIeG2VAgMB\nAAGjODA2MAwGA1UdEwEB/wQCMAAwDgYDVR0PAQH/BAQDAgeAMBYGA1UdJQEB/wQM\nMAoGCCsGAQUFBwMCMA0GCSqGSIb3DQEBBQUAA4IBAQBmN9o329nrd7GI0kn+f6tY\npMeaClWy0/DxV1b5wCPM4MrzhWLhWVFVDmbMs4IoazLeKuA6YypdcRJadKQ0nZuQ\njGvgs1mbdzr1w+KSS1WDkGHauaBMxbxM8PqaSkfEnLwBJysfenyi865wuTZaLU9J\nD3MUfo2EpMuZM57RAkOSFUT3+SmyC1XdRLPn0n7co66Lv6mGfuIX6dF7NtizjqdO\nSHCRatWQ4SiQjByBpHUrHkomU2Hcx7B57ATg2hHjCcpPM6dprZ7xKDI0PYN1bko8\nusPR35sR6qyq392xyxBiWUsOu/6Qj3y/4111u5Y+rZ3WnsuyFZzSpxrUHk9Nunyi\n-----END CERTIFICATE-----\n");
  myLog("3.7");
  client.connect(url.c_str(), 443, 5);
  myLog("4");
  HTTPClient http;
  myLog("4.5");
  http.begin(client, url.c_str());

  myLog("5");

  int resCode = http.PUT("true");
  if(resCode == HTTP_CODE_OK){
    myLog("Notify alarm success");
  } else {
    myLog("Notify alarm failure");
  }
}