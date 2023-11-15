FROM openjdk:17-jdk-slim

COPY target/Test-spring-app-0.0.1-SNAPSHOT.jar /Test-spring-app.jar

CMD ["java" , "-jar", "/Test-spring-app.jar"]
