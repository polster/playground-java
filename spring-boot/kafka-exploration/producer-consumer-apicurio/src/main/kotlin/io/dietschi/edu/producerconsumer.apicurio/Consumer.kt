package io.dietschi.edu.producerconsumer.apicurio

import io.dietschi.edu.producerconsumer.schema.avro.Event
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class Consumer {

    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(
        topics = [
            "apicurio-event-topic"
        ],
        groupId = "\${spring.kafka.consumer.group-id}"
    )
    fun processMessage(event: Event) {
        logger.info("Event received: {}", event)
    }
}