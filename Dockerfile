# Use a lightweight JDK image
FROM openjdk:17-jdk-slim

#Set working directory
WORKDIR /app

#Copy the jar file (build this via maven/gradle)
COPY build/libs/*.jar app.jar

# Expose port
EXPOSE 8080

# Debug
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]