FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/backend-0.0.1-SNAPSHOT.jar app.jar


RUN echo '#!/bin/bash\n\
echo "Esperando la base de datos MYSQL..."\n\
sleep 10\n\
echo "Inicializando aplicacion..."\n\
exec java -Djava.awt.headless=true -jar /app/app.jar' > /app/wait-for-database.sh


RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    bash \
    fontconfig \
    libfreetype6 \
    libfontconfig1 \
    fonts-dejavu \
    fonts-dejavu-core \
    fonts-dejavu-extra \
    && chmod +x /app/wait-for-database.sh \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*


RUN mkdir -p /usr/share/fonts/truetype/custom

EXPOSE 8080


ENV JAVA_OPTS="-Djava.awt.headless=true"

CMD ["/app/wait-for-database.sh"]