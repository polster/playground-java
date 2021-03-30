package io.dietschi.edu.projections.domain.order

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: MongoRepository<Order, String> {
}