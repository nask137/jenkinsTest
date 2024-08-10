FROM openjdk:latest
LABEL authors="睡个好觉"
COPY target/summer_practice-1.jar /test/summer_practice-1.jar
WORKDIR /test
CMD ["java", "-jar", "summer_practice-1.jar"]