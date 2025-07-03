# Stage 1: Use Maven for building
FROM maven:3.8.5-openjdk-17-slim as build
WORKDIR /app

COPY ./notificaitonservice ./notificaitonservice
COPY ./commonservice ./commonservice

WORKDIR /app/commonservice
RUN mvn clean install -DskipTests

WORKDIR /app/notificaitonservice
RUN mvn clean package -DskipTests

# Stage 2: use OpenJDK for running
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/notificaitonservice/target/*.jar notificaitonservice.jar
EXPOSE 9003
ENTRYPOINT ["java","-jar","notificaitonservice.jar"]