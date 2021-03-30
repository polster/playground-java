package io.dietschi.edu.projections

import io.dietschi.edu.projections.domain.general.toAddress
import io.dietschi.edu.projections.domain.order.Article
import io.dietschi.edu.projections.domain.order.Order
import io.dietschi.edu.projections.schema.avro.AddressChangedEvent
import io.dietschi.edu.projections.schema.avro.OrderChangedEvent

fun toOrder(orderChangedEvent: OrderChangedEvent,
            addressChangedEvent: AddressChangedEvent) : Order {

    val state = orderChangedEvent.orderChangedState
    val orderId = state.orderId
    return Order(
        orderChangedEvent.universalId,
        orderId,
        state.articles.map { article -> article.toArticle(orderId) },
        addressChangedEvent.toAddress()
    )
}

fun io.dietschi.edu.projections.schema.avro.Article.toArticle(orderId: String) : Article {
    return Article(
        orderId,
        this.articleId,
        this.quantity,
        this.name
    )
}