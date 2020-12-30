package io.dietschi.edu.producerconsumer.simple

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProducerConsumerSimpleApplication

fun main(args: Array<String>) {
	runApplication<ProducerConsumerSimpleApplication>(*args)
}
