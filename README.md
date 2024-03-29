# Showcase api first and open api generator

[![Java CI with Maven](https://github.com/shavo007/api-first-demo/actions/workflows/ci.yml/badge.svg)](https://github.com/shavo007/api-first-demo/actions/workflows/ci.yml)
[![Workflow Linting](https://github.com/shavo007/api-first-demo/actions/workflows/workflow-lint.yml/badge.svg)](https://github.com/shavo007/api-first-demo/actions/workflows/workflow-lint.yml)

## Pre-requisites

- Install [sdkMan](https://sdkman.io/)
- Install [Insomnia](https://insomnia.rest/)
- Install [NodeJS](https://nodejs.org/en/download/)
- Install [Docker](https://docs.docker.com/desktop/install/mac-install/)

```bash
sdk install java #how to choose which jdk https://whichjdk.com/#tldr
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
npx @stoplight/spectral-cli lint src/main/resources/oas3.yaml --ruleset greetings.spectral.yml
```

### CI (github actions)

#### Workflow linting

```bash
brew install actionlint
actionlint
```

#### Checkov

- docker file [policies](https://www.checkov.io/5.Policy%20Index/dockerfile.html)
- [suppressions](https://www.checkov.io/2.Basics/Suppressing%20and%20Skipping%20Policies.html)

#### Finding commit sha (security hardening for actions)

Usually, you want to pin to the commit SHA of a specific release. To find a release's commit SHA, go to the action's repository releases page (e.g. <https://github.com/actions/checkout/releases>). Find the release you want to use and click on the shorthand SHA (e.g. a12a394) listed in the summary section to the left of the release. You'll then be redirected to the release details page, which lists the full commit SHA you can use.

## Docker best practices

<https://aws.github.io/aws-eks-best-practices/security/docs/image/#recommendations>

### Non root user

```bash
docker run -it --rm -v "$PWD/Dockerfile":/Dockerfile:ro redcoolbeans/dockerlint #lint dockerfile
docker build -t shanelee007/api-first-demo . #build docker image
docker run -d -p8080:8080 shanelee007/api-first-demo #run image locally on 8080
```

```bash
2023-02-16T10:22:06.820Z INFO 1 --- [ main] c.e.greetings.GreetingsApplication : Starting GreetingsApplication v0.0.1-SNAPSHOT using Java 17.0.6 with PID 1 (/app/server.jar started by appuser in /app)
#The first log line mentions now that the application is started by appuser and not root.
```

docker run --tty --rm bridgecrew/checkov --directory /tf
docker run --tty --rm bridgecrew/checkov --directory /test-infra
