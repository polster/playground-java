package io.dietschi.edu.sb.hexagonbasics.infrastructure.messaging

import io.dietschi.edu.sb.hexagonbasics.domain.order.model.Order
import io.dietschi.edu.sb.hexagonbasics.domain.order.port.OrderEventProducer
import io.dietschi.edu.sb.hexagonbasics.infrastructure.messaging.aws.OrderEventSender
import io.dietschi.edu.sb.hexagonbasics.infrastructure.messaging.event.OrderCompletedEvent
import io.dietschi.edu.sb.hexagonbasics.infrastructure.messaging.event.OrderCreatedEvent
import org.springframework.stereotype.Component

@Component
class OrderEventProducerImpl(
    private val orderEventSender: OrderEventSender
): OrderEventProducer {

    override fun orderCreated(order: Order) {
        val orderCreated = OrderCreatedEvent(
            order.id
        )
        orderEventSender.sendOrderStatusEvent(
            orderCreated
        )
    }

    override fun orderCompleted(order: Order) {

        val orderCompleted = OrderCompletedEvent(
            order.id
        )
        orderEventSender.sendOrderStatusEvent(
            orderCompleted
        )
    }
}