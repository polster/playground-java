package io.dietschi.edu.sb.hexagonbasics.infrastructure.repository.mongo

import io.dietschi.edu.sb.hexagonbasics.domain.order.model.Order
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "orders")
data class OrderDocument(
    val orderId: String,
    val order: Order,
    val creationDate: Instant = Instant.now()
)