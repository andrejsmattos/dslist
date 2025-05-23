# Build stage
FROM openjdk:21-jdk-slim as build

WORKDIR /app

# Copy Maven wrapper and pom.xml first (for dependency caching)
COPY .mvn .mvn
COPY mvnw mvnw.cmd pom.xml ./

# Give execution permission to mvnw
RUN chmod +x ./mvnw

# Download dependencies (cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application (simplified command)
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM openjdk:21-jre-slim

WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Railway usa PORT environment variable)
EXPOSE $PORT

# Run the application
CMD ["java", "-jar", "app.jar"]