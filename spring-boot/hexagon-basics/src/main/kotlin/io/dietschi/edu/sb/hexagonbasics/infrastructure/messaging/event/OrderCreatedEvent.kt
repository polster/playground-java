package io.dietschi.edu.sb.hexagonbasics.infrastructure.messaging.event

import java.util.UUID

class OrderCreatedEvent(orderId: UUID): OrderEvent(
    orderId,
    OrderStatus.CREATED
)
