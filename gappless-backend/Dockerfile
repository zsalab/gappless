FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/gappless-backend-1.0.0.jar gappless-backend.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /gappless-backend.jar
