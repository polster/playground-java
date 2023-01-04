package io.dietschi.edu.sb.hexagonbasics.domain.order.port

import io.dietschi.edu.sb.hexagonbasics.domain.order.model.Order
import java.util.*

interface OrderRepository {

    fun findAll(): List<Order>
    fun findByOrderId(id: UUID): Order?
    fun save(order: Order)
}