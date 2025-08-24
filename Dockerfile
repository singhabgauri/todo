# ---- Build stage ----
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# ---- Run stage ----
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV SPRING_OUTPUT_ANSI_ENABLED=never
ENV SPRING_PROFILES_ACTIVE=prod
ENV PORT=8080
EXPOSE 8080
CMD ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]
