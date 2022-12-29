package io.dietschi.edu.sb.hexagonbasics.application.messaging.command

import java.util.*

data class Product(
    val id: UUID,
    val name: String,
    val price: Double
)
