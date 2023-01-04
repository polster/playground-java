package io.dietschi.edu.sb.hexagonbasics.domain.order.model

import java.util.UUID

data class OrderItem(
    val productId: UUID,
    val name: String,
    val price: Double
)
