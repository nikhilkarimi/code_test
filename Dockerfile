# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven executable JAR file and the POM file
#COPY target/employee-0.0.1-SNAPSHOT.jar /app/app.jar
COPY . .

# Expose port 8080
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "target/employee-0.0.1-SNAPSHOT.jar"]
