# Starfleet HQ (Spring Boot + Maven multi-module)

Small example project with two Spring Boot services and a shared `common` module.

## Modules

- `common`: shared model(s)
- `ship-service`: `GET /ship` returns a random ship and also POSTs it to logbook
- `logbook-service`: `POST /log` accepts a ship and writes a "captain's log" entry to the app logs

## Run locally (Maven)

From the repo root:

1) Start logbook-service (default port 8082)
   - `mvn -pl logbook-service -am spring-boot:run`

2) Start ship-service (default port 8081)
   - `mvn -pl ship-service -am spring-boot:run`

3) Test
   - `GET http://localhost:8081/ship`
   - `POST http://localhost:8082/log` with JSON:
     ```json
     { "name": "Voyager", "destination": "Europa", "speed": 7.5 }
     ```

Ports are set in:
- `ship-service/src/main/resources/application.properties`
- `logbook-service/src/main/resources/application.properties`

## OpenTelemetry (local collector)

This repo includes a local OpenTelemetry Collector that prints received traces to its own logs.

1) Start the collector:
   - `docker compose up`

2) (Optional) Run services with the OpenTelemetry Java agent
   - Download the agent jar 
     - `curl -L -o opentelemetry-javaagent.jar https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar`

   - In IntelliJ Run Configuration -> VM options (example for ship-service):
     - `-javaagent:opentelemetry-javaagent.jar`
     - `-Dotel.service.name=ship-service`
     - `-Dotel.exporter.otlp.endpoint=http://localhost:4318`
     - `-Dotel.exporter.otlp.protocol=http/protobuf`

   - Do the same for logbook-service (service name `logbook-service`).

3) Hit `GET /ship` and check the collector logs for spans.

