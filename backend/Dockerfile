FROM openjdk:17-jdk-slim AS build
VOLUME /tmp
COPY ./src /app/src
COPY ./pom.xml /app
WORKDIR /app
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]