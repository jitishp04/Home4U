FROM arm32v7/ubuntu:latest

# *Inspired by ChatGPT*

# Install required packages
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y curl git flex libc-dev && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Download and extract the arduino-cli binary
RUN curl -fsSL https://downloads.arduino.cc/arduino-cli/arduino-cli_latest_Linux_ARMv7.tar.gz -o arduino-cli.tar.gz && \
    tar -xzf arduino-cli.tar.gz && \
    rm arduino-cli.tar.gz

# Makes arduino-cli runnable from anywhere
RUN mv arduino-cli /usr/local/bin/arduino-cli && \
    chmod 777 /usr/local/bin/arduino-cli


# Manually installs some arduino libraries that aren't available in the manager
WORKDIR /arduino_lib
RUN curl -LJO "https://github.com/Seeed-Studio/Seeed_Arduino_FreeRTOS/archive/refs/tags/v1.1.zip"
RUN curl -LJO "https://github.com/Seeed-Studio/Seeed_Arduino_LCD/archive/refs/heads/master.zip"


# Setup file structure
COPY . /wio_terminal
WORKDIR /wio_terminal
RUN mkdir /build

# Adds board
RUN arduino-cli config init --
RUN arduino-cli config add board_manager.additional_urls https://files.seeedstudio.com/arduino/package_seeeduino_boards_index.json
RUN curl -o /root/.arduino15/package_seeeduino_boards_index.json https://files.seeedstudio.com/arduino/package_seeeduino_boards_index.json
RUN arduino-cli core install Seeeduino:samd
# To be able to install zip libraries
RUN arduino-cli config set library.enable_unsafe_install true


# Adds arduino libraries
RUN arduino-cli lib install --zip-path "/arduino_lib/Seeed_Arduino_FreeRTOS-1.1.zip"
RUN arduino-cli lib install --zip-path "/arduino_lib/Seeed_Arduino_LCD-master.zip"

RUN arduino-cli lib install "Seeed Arduino FS"
RUN arduino-cli lib install "Seeed Arduino rpcUnified"
RUN arduino-cli lib install "Seeed Arduino rpcWiFi"
RUN arduino-cli lib install "PubSubClient"
RUN arduino-cli lib install "Seeed Arduino SFUD"
RUN arduino-cli lib install "Seeed_Arduino_mbedtls"


# Compile your sketches using the arduino-cli tool
RUN arduino-cli compile -t --fqbn Seeeduino:samd:seeed_wio_terminal --build-path "/wio_terminal/build" .