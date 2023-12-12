#stage 1
#Start with a base image containing Java runtime
FROM openjdk:17-alpine

# Add Maintainer Info
LABEL maintainer="Kai <kai@signblast.com.au>"

WORKDIR /app

# The application's jar file
ARG JAR_FILE
# The application's shell file
ARG SHELL_FILE

# unpackage jar file
RUN apk update && apk upgrade && apk add netcat-openbsd && apk add curl
RUN mkdir -p /app

# Add the application's jar to the container
COPY ${JAR_FILE} /app/app.jar
# Add the application's shell to the container
COPY ${SHELL_FILE} /app/run.sh

# stage 2

# Add volume pointing to /tmp
VOLUME /tmp

# Grant all file
RUN chmod +x /app/*

# execute the application
CMD /app/run.sh
# Please note that the JCE policy files are no longer required for Java 17, so the corresponding commands have been removed from the Dockerfile.