# Read-Model projections

This example aims demonstrating a simple read-model projection, covering:

* Multiple stream-tasks
* Consumption from multiple topics as well as from the same, multiple times
* KTable join
* Write read-models to DB (MongoDB)

## Get started

* Make sure the needed [infrastructure](../infrastructure/README.md) is up and running
* Build:
  ```bash
  mvn clean install
  ```
* Create needed topics
  ```bash
  cd infrastructure/docker
  
  make kafka-topics-create
  ```
* Use the included IntelliJ run config to start the application (see [ReadModelProjectionsApplication](../.run/ReadModelProjectionsApplication.run.xml))
* Generate some test messages by executing the IntelliJ [MessageProducerKt](../.run/MessageProducerKt.run.xml) run config
* Check logs
* Open the following URLs in your favorite browser, to check contents:
  * [kafdrop](http://localhost:8083/)
  * [mongo-express](http://localhost:8082/)

## Hints

### Dockerize

This sub-module includes a [makefile](makefile), containing useful commands for local dockerization...