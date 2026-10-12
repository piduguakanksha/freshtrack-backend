# Stage 1: Build the Spring Boot application using Java 21 and Maven
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /build

# Copy Maven wrapper and pom.xml first for Docker layer caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Ensure line endings are Unix compatible and wrapper is executable
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

# Resolve dependencies in a cached layer
RUN ./mvnw dependency:go-offline -B || true

# Copy source code and build executable JAR (skip tests during container image build)
COPY src src
RUN ./mvnw clean package -DskipTests -B

# Normalize output JAR name so startup command does not assume exact version/filename
RUN cp target/*.jar app.jar

# Stage 2: Minimal Java 21 runtime image
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Run as non-root user for security best practices
RUN groupadd -r appuser && useradd -r -g appuser appuser
USER appuser:appuser

# Copy executable JAR from builder stage
COPY --from=builder /build/app.jar app.jar

# Render provides the PORT environment variable; default to 8080 if absent
ENV PORT=8080
EXPOSE 8080

# Execute Spring Boot application respecting dynamic PORT
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar app.jar"]
