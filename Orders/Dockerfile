# Use an OpenJDK 17 base image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy project dependencies and jar
COPY target/Orders-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]