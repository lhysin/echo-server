FROM gcr.io/distroless/java21-debian12

WORKDIR /app

COPY build/libs/echo-server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
