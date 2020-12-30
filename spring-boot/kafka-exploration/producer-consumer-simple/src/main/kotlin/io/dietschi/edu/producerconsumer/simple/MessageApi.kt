package io.dietschi.edu.producerconsumer.simple

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MessageApi(
    private val producer: Producer
) {

    @PostMapping("/message")
    fun publish(@RequestBody message: String) = producer.send(message)
}