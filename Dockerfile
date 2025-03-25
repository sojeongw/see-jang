FROM openjdk:21-jdk-slim

COPY target/seejang-0.0.1-SNAPSHOT.jar /app/seejang-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/seejang-0.0.1-SNAPSHOT.jar"]