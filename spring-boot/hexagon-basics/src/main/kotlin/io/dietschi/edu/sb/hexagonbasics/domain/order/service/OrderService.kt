package io.dietschi.edu.sb.hexagonbasics.domain.order.service

import io.dietschi.edu.sb.hexagonbasics.domain.order.model.Order
import io.dietschi.edu.sb.hexagonbasics.domain.order.model.Product
import java.util.UUID

interface OrderService {

    fun newOrder(product: Product)
    fun addProduct(id: UUID, product: Product)
    fun removeProduct(id: UUID, productId: UUID)
    fun completeOrder(id: UUID)
    fun orderById(id: UUID): Order
    fun orders(): List<Order>
}