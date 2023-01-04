package io.dietschi.edu.sb.hexagonbasics.infrastructure.repository

import io.dietschi.edu.sb.hexagonbasics.domain.order.port.OrderRepository
import io.dietschi.edu.sb.hexagonbasics.domain.order.model.Order
import io.dietschi.edu.sb.hexagonbasics.infrastructure.repository.mongo.OrderDocument
import io.dietschi.edu.sb.hexagonbasics.infrastructure.repository.mongo.OrderRepositoryMongo
import io.dietschi.edu.sb.hexagonbasics.unwrap
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderRepositoryImpl(
    private val orderRepositoryMongo: OrderRepositoryMongo
): OrderRepository {
    override fun findAll(): List<Order> {
        return orderRepositoryMongo
            .findAll()
            .map { it.order }
            .toList()
    }

    override fun findByOrderId(id: UUID): Order? {
        return orderRepositoryMongo
            .findByOrderId(id.toString())
            .unwrap()
            ?.let { return it.order }
    }

    override fun save(order: Order) {
        orderRepositoryMongo.save(
            OrderDocument(
                order.id.toString(),
                order
            )
        )
    }
}