package io.dietschi.edu.sb.hexagonbasics.application.web

import io.dietschi.edu.sb.hexagonbasics.domain.order.model.Order
import io.dietschi.edu.sb.hexagonbasics.domain.order.service.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService
) {

    @GetMapping("/{id}")
    fun orderById(@PathVariable id: UUID): Order {
        return orderService.orderById(id)
    }

    @GetMapping
    fun orders(): OrdersResponse {
        val orders = orderService
            .orders()
            .map { OrdersResponse.Order(
                it.id,
                it.getStatus())
            }
        return OrdersResponse(orders)
    }
}