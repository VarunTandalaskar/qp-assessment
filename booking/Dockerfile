# Use an official Java runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file built by your Spring Boot application
COPY build/libs/booking-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]