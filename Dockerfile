FROM openjdk:latest
LABEL authors="睡个好觉"
COPY target/summer_practice-1.jar /test/summer_practice-1.jar
WORKDIR /test
 ARG ACCESS_KEY_ID
 ARG ACCESS_KEY_SECRET
 ARG USER_SECRET_KEY

 ENV ACCESS_KEY_ID=${ACCESS_KEY_ID}
 ENV ACCESS_KEY_SECRET=${ACCESS_KEY_SECRET}
 ENV USER_SECRET_KEY=${USER_SECRET_KEY}

 EXPOSE 9001
CMD ["java", "-jar", "summer_practice-1.jar"]