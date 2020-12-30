package io.dietschi.edu.producerconsumer.apicurio

import io.dietschi.edu.producerconsumer.schema.avro.Event
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class Producer(
    private val kafkaTemplate: KafkaTemplate<String, Event>
) {

    fun send(event: Event) = kafkaTemplate.send(
        "apicurio-event-topic",
        event
    )
}