# Home4U - Group 16
## Description
HOME4U is a smart home system that provides users with the experience of monitoring home security from a distance and enjoying smart control of their home through devices. This involves:
- Set security mode from Wio terminal and app
- Turn off alarm through app and Wio terminal
- Interact (play, pause, skip) songs via app and Wio terminal 
- Create scenes (though unable to execute, open to development)

## Setup and Get Started
### Material
Hardware requirement:
- Wio terminal
- Grove - Speaker
- Grove - Mini PIR Motion Sensor
- Android phone
- Laptop or computer

### Setup
![Wio Terminal Setup](README-images/WioTerminalSetupImage.jpg)*Speaker (left), mini PIR motion sensor (right) connected to Wio terminal*

The following image above shows the setup for the Wio terminal to your computer using an USB-C port. Make sure the speaker is connected to the left side and motion sensor to the right side unless code has been altered.

<details>
<summary>Detailed Setup</summary>

1. creates myEnv.h in wio_terminal lib folder

```
cd wio_terminal/lib && echo "#define SSID \"\"\n#define SERVER_URL \"\"\n#define WIFI_PASSWORD \"\"\n#define BROKER_IP \"\"" > myEnv.h
```
2. Fill in the missing information in myEnv such as SSID, WIFI_PASSWORD, SERVER_URL, and BROKER_ID (this shouldn't contain port or protocol)

3. Start server using: 
```
cd node_server
npm install
node app
```

4. Head to ServerConnectionHelper URL variable (android/app/src/main/java/com/example/home4u/connectivity/ServerConnectionHelper.java) and set it to the backend server URL

5. Head to BrokerConnection BROKERHOST variable (android/app/src/main/java/com/example/home4u/connectivity/BrokerConnection.java) and set it to the broker IP address without protocol or port

### Automated Build
**For the android application:**
```
cd android && ./gradlew build
```
**For Wio Terminal:**
```
docker build -t wio_terminal_image ./wio_terminal
```
*This will build the app inside a Docker image. To extract the built file, also run:*
```
docker run --name wio_terminal_container wio_terminal_image
docker cp wio_terminal_container:wio_terminal/build/wio_terminal.ino.bin .
docker stop wio_terminal_container
docker rm wio_terminal_container
```

 
</details>

## Hardware and Software Architecture
[//]: <> (Add image for software and hardware architecture)
![Component Diagram](README-images/Component_Diagram.jpg)*Component Diagram for Wio Terminal & Android Application*

## Personal Contributions
| Name           | Contributions|
|----------------|-------------------------|
| Jitish Padhya  |Create scenes page, implement front-end, and back-end for new scene creator along with SQLite database [#7](https://git.chalmers.se/courses/dit113/2023/group-16/group-16/-/issues/7), worked on executing scenes [#26 (out-of-scope)](https://git.chalmers.se/courses/dit113/2023/group-16/group-16/-/issues/26), and final README file[#37](https://git.chalmers.se/courses/dit113/2023/group-16/group-16/-/issues/37) | 


## Visuals
<details>
<summary>App visuals</summary>

|            |              |
|----------------|-------------------------|
|![Home screen](README-images/HomeScreen.jpg)*Home screen* |![New sceen screen](README-images/CreateAndUpdateSceneScreen.jpg)*Create scenes*  |
| ![Manage scenes](README-images/ManageScenesScreen.jpg)*Manage scenes*| ![Song player](README-images/SongPlayerScreen.jpg)*Song player* |
</details>

<details>
<summary>Wio terminal visuals</summary>

![Wio terminal home screen](README-images/WioTerminalHomeScreen.jpg)*Wio terminal home screen*

The wio terminal has more screens that provide information such as alarm is actived or diactived, and it being triggered.
 
</details>

## Project status and Support
The project is stopped completely after 21/05/2023 hence there are unfinished requirements and its associated features that weren't able to be accomplished in the given time frame. Though, an MVP is available for everyone!
