# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . /app

# Build the project using Maven
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean install

# Run the application
CMD ["java", "-jar", "target/housing-platform.jar"]