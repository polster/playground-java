package io.dietschi.edu.sb.hexagonbasics.application.messaging

import io.dietschi.edu.sb.hexagonbasics.application.messaging.command.NewOrderCommand
import io.dietschi.edu.sb.hexagonbasics.domain.order.service.OrderService
import org.slf4j.LoggerFactory.getLogger
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class IncomingMessageListener(
    private val orderService: OrderService
) {

    @SqsListener(
        "#{environment['messaging.aws.in.queue']}",
        deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS
    )
    fun newOrder(message: NewOrderCommand) {

        logger.info("RAW Message received: $message")
        orderService.newOrder(
            message.toDomain()
        )
    }

    companion object {
        private val logger = getLogger(IncomingMessageListener::class.java)
    }
}