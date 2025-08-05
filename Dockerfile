FROM eclipse-temurin:21-jre

WORKDIR /app

COPY build/libs/echo-server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java \
  -Xms${JVM_XMS:-128m} \
  -Xmx${JVM_XMX:-256m} \
  -XX:MaxMetaspaceSize=${JVM_MAX_META:-128m} \
  -Dspring.profiles.active=${ACTIVE_PROFILES:-default} \
  -Dspring.application.name=${SPRING_APPLICATION_NAME:-myapp} \
  -jar app.jar"]