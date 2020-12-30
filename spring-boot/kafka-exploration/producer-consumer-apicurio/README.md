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