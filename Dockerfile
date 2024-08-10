FROM openjdk:latest
LABEL authors="睡个好觉"
COPY target/Summer_practice-0.0.1-SNAPSHOT.jar /test/Summer_practice-0.0.1-SNAPSHOT.jar
WORKDIR /test
CMD ["java", "-jar", "Summer_practice-0.0.1-SNAPSHOT.jar"]