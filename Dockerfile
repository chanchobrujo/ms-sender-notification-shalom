FROM openjdk:17

VOLUME /tmp
EXPOSE 8002
ARG FILE_NAME=target/ms-sender-notification-0.1.1.jar
ADD ${FILE_NAME} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]