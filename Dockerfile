
FROM openjdk:17-alpine

COPY ./build/libs/community-1.0.jar app.jar


CMD ["java", "-jar", "/app.jar"]
