FROM openjdk:8-jdk-alpine
ARG JAR_FILE=**/*.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]