# Use a JDK for building the application
FROM eclipse-temurin:22-jdk AS build

# Install required packages
RUN apt-get update \
  && apt-get install -y ca-certificates curl git openssh-client --no-install-recommends \
  && rm -rf /var/lib/apt/lists/*

# Set environment variables
ENV MAVEN_VERSION=3.9.9
ENV MAVEN_HOME=/usr/share/maven
ENV USER_HOME_DIR="/root"
ENV MAVEN_CONFIG="$USER_HOME_DIR/.m2"

# Copy Maven binaries from the official Maven image
COPY --from=maven:3.9.9-eclipse-temurin-17 ${MAVEN_HOME} ${MAVEN_HOME}
COPY --from=maven:3.9.9-eclipse-temurin-17 /usr/local/bin/mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
COPY --from=maven:3.9.9-eclipse-temurin-17 /usr/share/maven/ref/settings-docker.xml /usr/share/maven/ref/settings-docker.xml

# Create a symlink for Maven
RUN ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn

# Set the working directory
WORKDIR /usr/app

COPY . .

# Build the application
RUN mvn clean package

# Create a runtime image using JRE
FROM eclipse-temurin:22-jre

# Set the working directory for the runtime
WORKDIR /app

# Copy the JAR file from the build stage
# The JAR file should be located in target/ directory, not classes/
COPY --from=build /usr/app/target/server-1.0-SNAPSHOT.jar Main.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "Main.jar"]
