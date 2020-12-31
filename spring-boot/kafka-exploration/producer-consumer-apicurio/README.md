# Producer-consumer demo with Apicurio registry

A small demo, showing how to integrate and use a message schema with Spring Boot, Kafka and Apicurio.

## Get started

* Make sure the needed [infrastructure](../infrastructure/README.md) is up and running
* Build and run this project:
  ```bash
  mvn clean install
  mvn spring-boot:run
  ```
* Post a test event and check logs:
  ```bash
  curl --location --request POST 'http://localhost:8080/api/event' \
  --header 'Content-Type: application/json' \
  --data-raw '{
       "name": "Han Solo",
       "description": "The falcon has landed",
       "creationDate": 1609294830
  }'
  ```

## User Manual

### Behind the scene

* The proper schema is being defined in the Avro format: [schema](src/main/resources/avro/schema)
* Using the ``avro-maven-plugin``, the related sources are being generated (see [target/generated-sources/avro](target/generated-sources/avro) for more details)
* Using the ``apicurio-registry-maven-plugin``, the schema is being uploaded to the Apicurio registry
* Appropriate Spring Boot producer- and consumer-configuration, allow the usage of schema validation, serialization and deserialization: [application.yml](src/main/resources/application.yml)

### Useful URLs

* Apicurio registry: ``http://<minikube ip>:30036/ui/artifacts``
* Artefacts on Apicurio: ``http://<minikube ip>:30036/api/artifacts``