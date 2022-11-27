package io.dietschi.edu.sb.hexagonbasics.domain.model

import java.util.*

class Order(
    val id: UUID,
    product: Product
) {
    private val status = OrderStatus.CREATED
    private val items = mutableListOf(
        OrderItem(product)
    )
    private var totalPrice = product.price

    fun complete() {
        validateState()
    }

    fun getOrderItems() = Collections.unmodifiableList(items)

    fun addOrder(product: Product) {
        validateState()
        items.add(
            OrderItem(product)
        )
        totalPrice += product.price
    }

    fun removeOrder(id: UUID) {
        validateState()
        val item = getItem(id)
        items.remove(item)
        totalPrice -= item.price
    }

    private fun validateState() {
        if (OrderStatus.COMPLETED == status) {
            throw IllegalStateException("Order with id $id already completed!")
        }
    }

    private fun getItem(id: UUID): OrderItem {
        return items.first { id == it.productId }
    }
}