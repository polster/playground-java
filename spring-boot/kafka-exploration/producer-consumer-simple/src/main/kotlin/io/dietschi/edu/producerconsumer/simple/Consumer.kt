package io.dietschi.edu.producerconsumer.simple

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class Consumer {

    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(
        topics = [
            "simple-message-topic"
        ],
        groupId = "simple-consumer"
    )
    fun processMessage(message: String) {
        logger.info("Message received: {}", message)
    }
}