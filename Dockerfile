FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app
COPY . /app
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy
ARG USERNAME=appuser
ARG USER_UID=1000
ARG USER_GID=$USER_UID
WORKDIR /app
RUN groupadd --gid $USER_GID $USERNAME \
  && useradd --uid $USER_UID --gid $USER_GID -m $USERNAME
USER $USERNAME
VOLUME /tmp
COPY --from=builder /app/target/*.jar /app/server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/server.jar"]
