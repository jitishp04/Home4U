# A docker container containing dependencies to build an android app
FROM circleci/android:api-30

# Sets the root dir of the application to /app.
COPY . /app
WORKDIR /app

# Handles user permissions to not run in to errors when building
USER root
RUN useradd -m -s /bin/bash my_user
RUN chown -R my_user /app/.gradle
USER my_user

# Build the app
RUN ./gradlew build