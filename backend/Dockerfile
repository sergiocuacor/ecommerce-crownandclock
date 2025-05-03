
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY backend/pom.xml .
COPY backend/src ./src

RUN mvn clean package -DskipTests


FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

COPY backend/wait-for-database.sh /app/wait-for-database.sh


RUN apt-get update && \
    apt-get install -y --no-install-recommends bash && \
    chmod +x /app/wait-for-database.sh && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

EXPOSE 8080


CMD ["/app/wait-for-database.sh"]