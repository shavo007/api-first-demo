FROM eclipse-temurin:19-jdk-alpine as builder

WORKDIR /app
COPY . /app
RUN ./mvnw clean package

FROM eclipse-temurin:19-jre-jammy
VOLUME /tmp  
WORKDIR /app  
COPY --from=builder /app/target/*.jar /app/server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/server.jar"]  
