package io.dietschi.edu.sb.hexagonbasics.domain.order.port

import io.dietschi.edu.sb.hexagonbasics.domain.order.model.Order

interface OrderEventProducer {

    fun orderCreated(order: Order)
    fun orderCompleted(order: Order)
}