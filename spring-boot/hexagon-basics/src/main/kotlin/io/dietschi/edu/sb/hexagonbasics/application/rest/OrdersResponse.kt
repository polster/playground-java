package io.dietschi.edu.sb.hexagonbasics.application.rest

import io.dietschi.edu.sb.hexagonbasics.domain.model.OrderStatus
import java.util.UUID

data class OrdersResponse(
    val orders: List<Order>
) {
    data class Order(
        val id: UUID,
        val status: OrderStatus
    )
}