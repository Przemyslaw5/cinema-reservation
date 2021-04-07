FROM openjdk:15-alpine
WORKDIR /app
ADD . .
RUN ./gradlew bootJar
ENTRYPOINT ["./wait-for-it.sh", "dev-cinema-backend-db:3306", "--", "java", "-jar", "-Dspring.profiles.active=prod","build/libs/cinema-backend-0.0.1-SNAPSHOT.jar"]