package io.dietschi.edu.sb.hexagonbasics

import io.dietschi.edu.sb.hexagonbasics.application.config.MessagingClientProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(
	MessagingClientProperties::class
)
class HexagonBasicsApplication

fun main(args: Array<String>) {
	runApplication<HexagonBasicsApplication>(*args)
}
