FROM arm32v7/ubuntu:latest

# *Inspired by ChatGPT*

# Install required packages
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y curl git libc-dev && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Download and extract the arduino-cli binary
RUN curl -fsSL https://downloads.arduino.cc/arduino-cli/arduino-cli_latest_Linux_ARMv7.tar.gz -o arduino-cli.tar.gz && \
    tar -xzf arduino-cli.tar.gz && \
    rm arduino-cli.tar.gz

# Makes arduino-cli runnable from anywhere
RUN mv arduino-cli /usr/local/bin/arduino-cli && \
    chmod 777 /usr/local/bin/arduino-cli

# Copies sketch files into container
COPY . /wio_terminal
WORKDIR /wio_terminal

# Adds board
RUN arduino-cli config init
RUN arduino-cli config add board_manager.additional_urls https://files.seeedstudio.com/arduino/package_seeeduino_boards_index.json
RUN curl -o /root/.arduino15/package_seeeduino_boards_index.json https://files.seeedstudio.com/arduino/package_seeeduino_boards_index.json
RUN arduino-cli core install Seeeduino:samd

# Compile your sketches using the arduino-cli tool
RUN arduino-cli compile --fqbn Seeeduino:samd:seeed_wio_terminal wio_terminal.ino