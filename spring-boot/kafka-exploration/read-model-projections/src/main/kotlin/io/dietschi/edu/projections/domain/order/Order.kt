package io.dietschi.edu.projections.domain.order

import io.dietschi.edu.projections.domain.general.Address
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(
    collection = "orders"
)
data class Order(val universalId: String,
                 @Id val orderId: String,
                 val articles: List<Article>,
                 val address: Address,
                 val createdDate: LocalDateTime = LocalDateTime.now()
) {
}