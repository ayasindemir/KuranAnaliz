# 1. Aşama: Maven ile projeyi derle
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Aşama: Yalnızca çalıştırılabilir JAR'ı taşı
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar KuranAnaliz.jar
ENTRYPOINT ["java", "-jar", "/app/KuranAnaliz.jar"]