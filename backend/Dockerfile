FROM openjdk:21-jdk-slim AS build

WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradlew .
RUN chmod +x gradlew
COPY gradle gradle
COPY settings.gradle .
COPY build.gradle .
COPY application application
COPY domain domain
COPY infrastructure infrastructure
COPY spring spring

# Build the Spring Boot jar from the 'spring' module
RUN ./gradlew :spring:bootJar --no-daemon

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/spring/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]