package io.dietschi.edu.projections

import io.apicurio.registry.utils.serde.AvroKafkaSerializer
import io.dietschi.edu.projections.schema.avro.*
import io.dietschi.edu.projections.schema.avro.Address
import io.dietschi.edu.projections.schema.avro.Article
import io.dietschi.edu.projections.schema.avro.Order
import io.dietschi.edu.projections.schema.avro.User
import org.apache.avro.specific.SpecificRecordBase
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

fun main(args : Array<String>) {

    val iterations: Int = args
        .filter { it.contains(CMD_PARAM_NUM_OF_ITERAIONS) }
        .map { it.split("=", limit = 2) }
        .map { (_, value) -> value }
        .map { it.toInt() }
        .first()
    val producer = producer()

    for (i in 1..iterations) {
        val universalId = uuid()
        val addressChangedEvent = AddressChangedEvent(
            universalId,
            LocalDateTime.now().toString(),
            Address(
                randomString(),
                randomNumber().toString()
            )
        )
        val userChangedEvent = UserChangedEvent(
            universalId,
            LocalDateTime.now().toString(),
            User(
                randomString(),
                randomString(),
                randomString()
            )
        )
        val orderChangedEvent = OrderChangedEvent(
            universalId,
            LocalDateTime.now().toString(),
            Order(
                uuid(),
                LocalDateTime.now().toString(),
                mutableListOf(
                    Article(
                        uuid(),
                        randomNumber(),
                        randomString()
                    )
                )
            )
        )

        producer.send(
            "address-changed-topic",
            addressChangedEvent.universalId,
            addressChangedEvent
        )
        producer.send(
            "user-changed-topic",
            userChangedEvent.universalId,
            userChangedEvent
        )
        producer.send(
            "order-changed-topic",
            orderChangedEvent.universalId,
            orderChangedEvent
        )
    }
}

fun producer(): KafkaTemplate<String, SpecificRecordBase> {

    val producerProps = mutableMapOf<String, Any>()
    producerProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
    producerProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
    producerProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = AvroKafkaSerializer::class.java
    producerProps["apicurio.registry.url"] = "http://localhost:8081/api"
    producerProps["apicurio.registry.artifact-id"] = "io.apicurio.registry.utils.serde.strategy.RecordIdStrategy"
    producerProps["apicurio.registry.global-id"] = "io.apicurio.registry.utils.serde.strategy.FindLatestIdStrategy"

    return KafkaTemplate(
        DefaultKafkaProducerFactory(
            producerProps
        )
    )
}

fun uuid(): String = UUID.randomUUID().toString()

fun randomNumber(): Int = Random.nextInt(1, 30)

fun randomString(): String = (1..10)
    .map { _ -> Random.nextInt(0, charPool.size) }
    .map(charPool::get)
    .joinToString("")

private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
private val CMD_PARAM_NUM_OF_ITERAIONS = "--iterations"