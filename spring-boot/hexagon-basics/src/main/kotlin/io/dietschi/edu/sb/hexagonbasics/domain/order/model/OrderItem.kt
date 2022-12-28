package io.dietschi.edu.sb.hexagonbasics.domain.order.model

data class OrderItem(
    val product: Product
) {
    val productId = product.id
    val price = product.price
}
