package io.dietschi.edu.sb.hexagonbasics.infrastructure.repository.mongo

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepositoryMongo: MongoRepository<OrderDocument, String>