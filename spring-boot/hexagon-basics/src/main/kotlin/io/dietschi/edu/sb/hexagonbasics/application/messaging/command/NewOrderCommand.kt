package io.dietschi.edu.sb.hexagonbasics.application.messaging.command

data class NewOrderCommand(
    val product: Product
) {
    val messageType: String = "new-order"
    val version: String = "v1"
}