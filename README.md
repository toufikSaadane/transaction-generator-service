# Transaction Generator Service

A Java Spring Boot service that periodically generates MT103 banking transaction files and publishes related messages to a Kafka topic.

## Key Features

- Generates formatted MT103 statements with random transaction references (using `MT103Field20Generator`)
- Builds random IBANs per EU country rules (`IbanGeneratorByCountry`)
- Writes `.sta` files to the configured output directory
- Publishes transaction messages to Kafka topic `transaction_generator` via `TransactionProducer`
- Configurable via `application.yaml`
- Runs on a fixed schedule (`MT103ScheduledJob`)
- SLF4J logging via Lombok (`@Slf4j`)

## Prerequisites

- Java 17+
- Gradle
- Kafka running at `localhost:9092`

## Configuration

Edit `src/main/resources/application.yaml` to set:

- `server.port` (default: `3001`)
- Kafka bootstrap servers and serializers
- `MT103.topic` (default: `transaction_generator`)
- `MT103.output-dir` (default: `output`)

## Build & Run

```bash
# Build the project
./gradlew clean build

# Run the service
./gradlew bootRun

/opt/homebrew/opt/kafka/bin/kafka-console-consumer --topic transaction_generator --bootstrap-server localhost:9092