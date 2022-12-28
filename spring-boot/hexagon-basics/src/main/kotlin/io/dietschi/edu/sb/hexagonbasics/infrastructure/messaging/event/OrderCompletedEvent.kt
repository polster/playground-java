package io.dietschi.edu.sb.hexagonbasics.infrastructure.messaging.event

import java.util.UUID

class OrderCompletedEvent(orderId: UUID): OrderEvent(
    orderId,
    OrderStatus.COMPLETED
)