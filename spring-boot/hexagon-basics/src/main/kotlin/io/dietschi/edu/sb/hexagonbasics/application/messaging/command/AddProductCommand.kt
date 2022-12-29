package io.dietschi.edu.sb.hexagonbasics.application.messaging.command

import java.util.UUID

data class AddProductCommand(
    val orderId: UUID,
    val product: Product
) {
    val messageType: String = "add-product"
    val version: String = "v1"
}
