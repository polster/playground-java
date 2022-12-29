package io.dietschi.edu.sb.hexagonbasics.application.messaging

import io.dietschi.edu.sb.hexagonbasics.application.messaging.command.AddProductCommand
import io.dietschi.edu.sb.hexagonbasics.application.messaging.command.NewOrderCommand
import io.dietschi.edu.sb.hexagonbasics.domain.order.model.Product

fun NewOrderCommand.toDomain() = Product(
    id = this.product.id,
    name = this.product.name,
    price = this.product.price
)

fun AddProductCommand.toDomain() = Product(
    id = this.product.id,
    name = this.product.name,
    price = this.product.price
)