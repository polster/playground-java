package io.dietschi.edu.sb.hexagonbasics.domain.service

import io.dietschi.edu.sb.hexagonbasics.domain.model.Order
import io.dietschi.edu.sb.hexagonbasics.domain.model.Product
import io.dietschi.edu.sb.hexagonbasics.domain.ports.OrderRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.IllegalStateException

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository
): OrderService {

    override fun newOrder(product: Product): UUID {
        val order = Order(
            UUID.randomUUID(),
            product
        )
        orderRepository.save(order)
        return order.id
    }

    override fun addProduct(id: UUID, product: Product) {
        val order = getOrder(id)
        order.addOrder(product)
        orderRepository.save(order)
    }

    override fun removeProduct(id: UUID, productId: UUID) {
        val order = getOrder(id)
        order.removeOrder(productId)
        orderRepository.save(order)
    }

    override fun completeOrder(id: UUID) {
        val order = getOrder(id)
        order.complete()
        orderRepository.save(order)
    }

    override fun orderById(id: UUID): Order {
        return getOrder(id)
    }

    override fun orders(): List<Order> {
        return orderRepository.findAll()
    }

    private fun getOrder(id: UUID): Order {
        orderRepository
            .findById(id)
            ?.let {
                return it
            } ?: throw IllegalStateException("Order with id $id not found!")
    }
}