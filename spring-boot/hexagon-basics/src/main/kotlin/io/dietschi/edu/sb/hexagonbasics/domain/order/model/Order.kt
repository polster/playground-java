package io.dietschi.edu.sb.hexagonbasics.domain.order.model

import org.springframework.data.annotation.PersistenceCreator
import java.util.*

class Order {

    val id: UUID
    private var items: MutableList<OrderItem>
    private var status: OrderStatus = OrderStatus.CREATED

    constructor(id: UUID,
                product: Product) {

        this.id = id
        this.items = mutableListOf(
            OrderItem(
                product.id,
                product.name,
                product.price
            )
        )
    }

    @PersistenceCreator
    constructor(id: UUID,
                items: MutableList<OrderItem>,
                status: OrderStatus) {

        this.id = id
        this.items = items
        this.status = status
    }

    fun complete() {
        validateStatus()
        status = OrderStatus.COMPLETED
    }

    fun getStatus() = status

    fun addOrder(product: Product) {
        validateStatus()
        items.add(
            OrderItem(
                product.id,
                product.name,
                product.price
            )
        )
    }

    fun removeOrder(id: UUID) {
        validateStatus()
        val item = getItem(id)
        items.remove(item)
    }

    private fun validateStatus() {
        if (OrderStatus.COMPLETED == status) {
            throw IllegalStateException("Order with id $id already completed!")
        }
    }

    private fun getItem(id: UUID): OrderItem {
        return items.first { id == it.productId }
    }
}