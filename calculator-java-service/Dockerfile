# Stage 1: Dependencies and Build
FROM maven:3.9-eclipse-temurin-17 AS dependencies
WORKDIR /app

COPY calculator-java-service/pom.xml .

RUN mvn dependency:go-offline -B

# Stage 2: Test & Build
FROM dependencies AS build
WORKDIR /app

COPY calculator-java-service/ .

RUN mvn clean test package

# Stage 3: Production
FROM eclipse-temurin:17-jre-alpine AS production
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV JAVA_OPTS="-Xms256m -Xmx512m"

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]