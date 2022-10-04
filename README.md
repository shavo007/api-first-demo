# Showcase api first and open api generator

## Pre-requisistes

- Install [sdkMan](https://sdkman.io/)
- Install [Insomnia](https://insomnia.rest/)
- Install [NodeJS]()

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

### Local

```bash
docker run --rm -t -v $(pwd):/data:ro tufin/oasdiff -format text -base /data/src/main/resources/oas3.yaml  -revision /data/src/main/resources/oas3.yaml
#- OR -
oasdiff -format text -base https://raw.githubusercontent.com/shavo007/api-first-demo/main/src/main/resources/oas3.yaml -revision src/main/resources/oas3.yaml
#https://raw.githubusercontent.com/shavo007/api-first-demo/main/src/main/resources/oas3.yaml
```

## Linting

Using [Spectral](https://meta.stoplight.io/docs/spectral/674b27b261c3c-overview)

### Local

```bash
npx @stoplight/spectral-cli lint src/main/resources/oas3.yaml --ruleset greetings.spectral.yml
```

### CI (github actions)

Linting is incorporated into the build pipeline. See [ci.yml](.github/workflows/ci.yml)
