FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app
COPY . /app
RUN ./mvnw install

FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp  
WORKDIR /app  
COPY --from=builder /app/target/*.jar /app/server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/server.jar"]  
