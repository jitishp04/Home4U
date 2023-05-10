FROM openjdk:17-jdk-slim

# Install required packages
RUN apt-get update && \
    apt-get install -y wget curl unzip

# Download and install Android SDK
RUN cd /opt && \
    wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip && \
    unzip commandlinetools-linux-9477386_latest.zip && \
    rm commandlinetools-linux-9477386_latest.zip

RUN cd /opt && \
    mkdir android-sdk && \
    cd android-sdk && \
    mkdir cmdline-tools && \
    mv /opt/cmdline-tools /opt/android-sdk/cmdline-tools/latest

# Set up environment variables
ENV ANDROID_HOME=/opt/android-sdk/cmdline-tools/latest
ENV PATH=$PATH:$ANDROID_HOME/bin:$ANDROID_HOME/platform-tools
# RUN mkdir /root/.android
# RUN touch /root/.android/repositories.cfg

# #Install required Android SDK components
RUN mkdir "$ANDROID_HOME/licenses"
RUN echo "24333f8a63b6825ea9c5514f83c2829b004d1fee" > "$ANDROID_HOME/licenses/android-sdk-license"
# RUN yes | sdkmanager --licenses
RUN yes | sdkmanager "platform-tools" "platforms;android-33"
RUN yes | sdkmanager --install "build-tools;33.0.0"


COPY . /app



# Build the app
#RUN cd /app && ./gradlew build