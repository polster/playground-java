package io.dietschi.edu.sb.hexagonbasics.application.messaging.command

import java.util.UUID

data class NewOrderCommand(
    val product: Product,
    val version: String = "v1"
) {

    data class Product(
        val id: UUID,
        val name: String,
        val price: Double
    )
}