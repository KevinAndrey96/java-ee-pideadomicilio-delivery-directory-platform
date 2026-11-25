# Multi-stage Dockerfile for PideADomicilio Platform
# Stage 1: Build WAR application with Maven & OpenJDK 8
FROM maven:3.8.8-eclipse-temurin-8 AS builder

WORKDIR /build

# Cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B || true

# Copy source tree and compile
COPY src ./src
RUN mvn clean package -DskipTests -DskipSass=true -B

# Stage 2: Runtime Container with WildFly Application Server
FROM jboss/wildfly:10.1.0.Final

# Create upload directory with appropriate permissions
USER root
RUN mkdir -p /opt/filespad && chown -R jboss:jboss /opt/filespad
USER jboss

# Copy built WAR artifact to WildFly deployment directory
COPY --from=builder /build/target/ROOT.war /opt/jboss/wildfly/standalone/deployments/ROOT.war

# Application environment configuration
ENV MONGO_HOST=mongo \
    MONGO_PORT=27017 \
    FILE_STORAGE_PATH=/opt/filespad \
    AES_SECRET_KEY=Inf12345Inf12345 \
    AES_INIT_VECTOR=RandomInftVector

EXPOSE 8080 9990

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
