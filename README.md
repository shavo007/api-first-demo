# Showcase api first and open api generator

[![Java CI with Maven](https://github.com/shavo007/api-first-demo/actions/workflows/ci.yml/badge.svg)](https://github.com/shavo007/api-first-demo/actions/workflows/ci.yml)
[![Workflow Linting](https://github.com/shavo007/api-first-demo/actions/workflows/workflow-lint.yml/badge.svg)](https://github.com/shavo007/api-first-demo/actions/workflows/workflow-lint.yml)

## Pre-requisites

- Install [sdkMan](https://sdkman.io/)
- Install [Insomnia](https://insomnia.rest/)
- Install [NodeJS](https://nodejs.org/en/download/)
- Install [Docker](https://docs.docker.com/desktop/install/mac-install/)

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

Using [Spectral](https://meta.stoplight.io/docs/spectral/674b27b261c3c-overview)

### Local

```bash
npx spectral lint src/main/resources/oas3.yaml --ruleset greetings.spectral.yml
```

### CI (github actions)

#### Workflow linting

```bash
brew install actionlint
actionlint
```

#### How to write better code

```ctrl + alt + del```
