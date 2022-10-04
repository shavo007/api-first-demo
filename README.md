# Showcase api first and open api generator

## Pre-requisistes

- Install [sdkMan](https://sdkman.io/)
- Install [Insomnia](https://insomnia.rest/)

```bash
sdk install java
sdk list java #list java versions
sdk current java
"java.jdt.ls.java.home": "/Users/<username>/.sdkman/candidates/java/17.0.4.1-tem" #VSCode settings
sdk use java 14.0.1.j9-adpt #if you want to switch versions for example
```

## Run locally

```bash
./mvnw clean install
./mvnw spring-boot:run
open http://localhost:8080/greetings # - OR - alternatively import OAS into insomnia and run the requests
```

## Test for breaking changes

## Linting

### Local

### CI (github actions)
