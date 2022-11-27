package io.dietschi.edu.sb.hexagonbasics.infrastructure.repository.mongo

import io.dietschi.edu.sb.hexagonbasics.domain.model.Order
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "orders")
data class OrderDocument(
    val order: Order,
    val creationDate: Instant
)