# Producer-consumer demo

A simple producer/consumer demo.

## Get started

* Make sure the needed [infrastructure](../infrastructure/README.md) is up and running
* Build and run this project:
  ```bash
  mvn clean install
  mvn spring-boot:run
  ```
* Post a test message and check logs:
  ```bash
  curl --location --request POST 'http://localhost:8080/api/event' \
  --header 'Content-Type: text/plain' \
  --data-raw 'Hello World'
  ```