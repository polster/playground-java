package io.dietschi.edu.sb.hexagonbasics.infrastructure.repository.mongo

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface OrderRepositoryMongo: MongoRepository<OrderDocument, String> {

    fun findByOrderId(orderId: String): Optional<OrderDocument>
}