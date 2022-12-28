package io.dietschi.edu.sb.hexagonbasics.infrastructure.messaging.event

import java.util.UUID

abstract class OrderEvent(
    val orderId: UUID,
    val status: OrderStatus
)
