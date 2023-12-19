FROM openjdk:17-jdk-alpine
ARG JAR_FILE=*.jar
COPY target/weatherRestService-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]