FROM openjdk:15-alpine
WORKDIR /app
ADD . .
RUN ./gradlew bootJar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod","build/libs/cinema-backend-0.0.1-SNAPSHOT.jar"]