package io.dietschi.edu.sb.hexagonbasics.domain.model

import java.util.*

class Order(
    val id: UUID,
    product: Product
) {
    private var status = OrderStatus.CREATED
    private val items = mutableListOf(
        OrderItem(product)
    )
    private var totalPrice = product.price

    fun complete() {
        validateStatus()
        status = OrderStatus.COMPLETED
    }

    fun getOrderItems() = Collections.unmodifiableList(items)

    fun getStatus() = status

    fun addOrder(product: Product) {
        validateStatus()
        items.add(
            OrderItem(product)
        )
        totalPrice += product.price
    }

    fun removeOrder(id: UUID) {
        validateStatus()
        val item = getItem(id)
        items.remove(item)
        totalPrice -= item.price
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