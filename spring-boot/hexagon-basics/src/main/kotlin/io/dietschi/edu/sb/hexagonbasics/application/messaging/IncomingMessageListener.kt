package io.dietschi.edu.sb.hexagonbasics.application.messaging

import io.dietschi.edu.sb.hexagonbasics.application.messaging.command.AddProductCommand
import io.dietschi.edu.sb.hexagonbasics.application.messaging.command.NewOrderCommand
import io.dietschi.edu.sb.hexagonbasics.domain.order.service.OrderService
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class IncomingMessageListener(
    private val orderService: OrderService
) {

    @SqsListener(
        "#{environment['messaging.aws.in.queue.newOrder']}",
        deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS
    )
    fun newOrder(newOrderCommand: NewOrderCommand) {

        orderService.newOrder(
            newOrderCommand.toDomain()
        )
    }

    @SqsListener(
        "#{environment['messaging.aws.in.queue.addProduct']}",
        deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS
    )
    fun addProduct(addProductCommand: AddProductCommand) {

        orderService.addProduct(
            addProductCommand.orderId,
            addProductCommand.toDomain()
        )
    }
}