package io.dietschi.edu.sb.hexagonbasics.application.web

import io.dietschi.edu.sb.hexagonbasics.domain.order.model.OrderStatus
import java.util.UUID

data class OrdersResponse(
    val orders: List<Order>
) {
    data class Order(
        val id: UUID,
        val status: OrderStatus
    )
}