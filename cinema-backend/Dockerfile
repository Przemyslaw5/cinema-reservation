FROM openjdk:15
WORKDIR /app
ADD . .
RUN ./gradlew bootJar
ENTRYPOINT ["java", "-jar", "build/libs/cinema-backend-0.0.1-SNAPSHOT.jar"]