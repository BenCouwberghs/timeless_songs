# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /build_dir
COPY . .
RUN mvn clean package

# Stage 2: Create minimal runtime image
FROM gcr.io/distroless/java21:nonroot
COPY --from=builder /build_dir/app/target/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]