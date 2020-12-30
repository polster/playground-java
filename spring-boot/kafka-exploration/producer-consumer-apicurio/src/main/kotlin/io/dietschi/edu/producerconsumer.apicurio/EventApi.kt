package io.dietschi.edu.producerconsumer.apicurio

import io.dietschi.edu.producerconsumer.schema.avro.Event
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.log

@RestController
@RequestMapping("/api")
class EventApi(
    private val producer: Producer
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/event")
    fun publish(@RequestBody event: Event) {

        logger.info("POST: /api/event")
        producer.send(event)
    }
}