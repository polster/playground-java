package io.dietschi.edu.projections.config

import io.dietschi.edu.projections.domain.ProjectorService
import io.dietschi.edu.projections.schema.avro.AddressChangedEvent
import io.dietschi.edu.projections.schema.avro.OrderChangedEvent
import io.dietschi.edu.projections.schema.avro.UserChangedEvent
import io.dietschi.edu.projections.toOrder
import io.dietschi.edu.projections.domain.user.toUser
import org.apache.kafka.streams.kstream.KTable
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.BiConsumer

@Configuration
class EventProcessingConfig {

    @Bean
    fun processUserEvents(projectorService: ProjectorService): BiConsumer<KTable<String, UserChangedEvent>, KTable<String, AddressChangedEvent>> =
        BiConsumer { userChangedEvents, addressChangedEvents ->
            userChangedEvents
                .join(addressChangedEvents) { userChangedEvent, addressChangedEvent ->
                    toUser(
                        userChangedEvent,
                        addressChangedEvent
                    )
                }
                .mapValues { user ->
                    projectorService.upsertUser(user)
                }
                .toStream()
                .foreach { key, value ->
                    logger.debug(
                        "Read model processed for key $key and universalId ${value.universalId}"
                    )
                }
        }

    @Bean
    fun processOrderEvents(projectorService: ProjectorService): BiConsumer<KTable<String, OrderChangedEvent>, KTable<String, AddressChangedEvent>> =
        BiConsumer { orderChangedEvents, addressChangedEvents ->
            orderChangedEvents
                .join(addressChangedEvents) { orderChangedEvent, addressChangedEvent ->
                    toOrder(
                        orderChangedEvent,
                        addressChangedEvent
                    )
                }
                .mapValues { order ->
                    projectorService.upsertOrder(order)
                }
                .toStream().foreach { key, value ->
                    logger.debug(
                        "Read model processed for key $key and universalId ${value.universalId}"
                    )
                }
        }

    companion object {
        private val logger = LoggerFactory.getLogger(EventProcessingConfig::class.java)
    }
}