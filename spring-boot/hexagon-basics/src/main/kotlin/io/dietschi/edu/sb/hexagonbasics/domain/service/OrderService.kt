package io.dietschi.edu.sb.hexagonbasics.domain.service

import io.dietschi.edu.sb.hexagonbasics.domain.model.Order
import io.dietschi.edu.sb.hexagonbasics.domain.model.Product
import java.util.UUID

interface OrderService {

    fun newOrder(product: Product): UUID
    fun addProduct(id: UUID, product: Product)
    fun removeProduct(id: UUID, productId: UUID)
    fun completeOrder(id: UUID)
    fun orderById(id: UUID): Order
    fun orders(): List<Order>
}