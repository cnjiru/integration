FROM maven:3.6.3-jdk-11 AS MAVEN_BUILD

MAINTAINER Charles Njiru

COPY pom.xml /build/

COPY src /build/src/

WORKDIR /build/

RUN mvn package -DskipTests=true

# For Java 8, try this
# FROM openjdk:8-jdk-alpine

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/africastalkingsms-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "africastalkingsms-0.0.1-SNAPSHOT.jar"]
