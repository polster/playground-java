package io.dietschi.edu.sb.hexagonbasics.domain.repository

import io.dietschi.edu.sb.hexagonbasics.domain.model.Order
import java.util.*

interface OrderRepository {

    fun findAll(): List<Order>
    fun findById(id: UUID): Order?
    fun save(order: Order)
}