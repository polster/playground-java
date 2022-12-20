package io.dietschi.edu.sb.hexagonbasics.application.messaging

import io.dietschi.edu.sb.hexagonbasics.domain.service.OrderService
import org.slf4j.LoggerFactory.getLogger
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class OrderHandler(
    private val orderService: OrderService
) {

    @SqsListener(
        "hex-order-commands",
        deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS
    )
    fun newOrder(message: NewOrderCommand) {
        logger.info("RAW Message received: $message")
    }

    companion object {
        private val logger = getLogger(OrderHandler::class.java)
    }
}