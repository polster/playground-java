package io.dietschi.edu.sb.hexagonbasics.infrastructure.messaging.aws

import io.dietschi.edu.sb.hexagonbasics.infrastructure.messaging.event.OrderEvent
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderEventSender(
    @Value("\${messaging.aws.out.topic}")
    private val outTopic: String,
    private val notificationMessageTemplate: NotificationMessagingTemplate
) {

    fun sendOrderStatusEvent(event: OrderEvent) {

        val message = MessageBuilder
            .withPayload(event)
            .build()

        notificationMessageTemplate.convertAndSend(outTopic, message)
        logger.info("Order status event message with id ${message.headers.id} sent")
    }

    companion object {
        private val logger = getLogger(OrderEventSender::class.java)
    }
}