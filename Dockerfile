# Use official OpenJDK 17 image as base
FROM openjdk:17-alpine

# Set working directory
WORKDIR /app

# Copy your jar file into the container
COPY render-deploy/parreplenishment-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
