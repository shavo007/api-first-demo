FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app
COPY . /app
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy
VOLUME /tmp  
WORKDIR /app  
COPY --from=builder /app/target/*.jar /app/server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/server.jar"]  
