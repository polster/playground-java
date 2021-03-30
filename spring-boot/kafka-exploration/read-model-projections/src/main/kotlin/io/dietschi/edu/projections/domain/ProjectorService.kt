package io.dietschi.edu.projections.domain

import io.dietschi.edu.projections.domain.order.Order
import io.dietschi.edu.projections.domain.order.OrderRepository
import io.dietschi.edu.projections.domain.user.User
import io.dietschi.edu.projections.domain.user.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectorService
@Autowired constructor(private val userRepository: UserRepository,
                       private val orderRepository: OrderRepository
)
{
    fun upsertUser(user: User): User {
        logger.debug("Add or update user with id: ${user.universalId}")
        return userRepository.save(user)
    }

    fun upsertOrder(order: Order): Order {
        logger.debug("Add or update order with id ${order.orderId} for user with id ${order.universalId}")
        return orderRepository.save(order)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ProjectorService::class.java)
    }
}