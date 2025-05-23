# Build stage
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /workspace/app
COPY . .
RUN ./gradlew build

# Runtime stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /workspace/app/build/libs/your-app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]