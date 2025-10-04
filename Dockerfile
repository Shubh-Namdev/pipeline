FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY build/libs/springboot-mysql-k8s-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
