package io.dietschi.edu.sb.hexagonbasics.domain.order.service

import io.dietschi.edu.sb.hexagonbasics.domain.order.model.Order
import io.dietschi.edu.sb.hexagonbasics.domain.order.model.Product
import io.dietschi.edu.sb.hexagonbasics.domain.order.port.OrderEventProducer
import io.dietschi.edu.sb.hexagonbasics.domain.order.port.OrderRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.IllegalStateException

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val orderEventProducer: OrderEventProducer
): OrderService {

    override fun newOrder(product: Product) {
        val order = Order(
            UUID.randomUUID(),
            product
        )
        orderRepository.save(order)
        orderEventProducer.orderCreated(order)
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
        orderEventProducer.orderCompleted(order)
    }

    override fun orderById(id: UUID): Order {
        return getOrder(id)
    }

    override fun orders(): List<Order> {
        return orderRepository.findAll()
    }

    private fun getOrder(id: UUID): Order {
        orderRepository
            .findByOrderId(id)
            ?.let {
                return it
            } ?: throw IllegalStateException("Order with id $id not found!")
    }
}