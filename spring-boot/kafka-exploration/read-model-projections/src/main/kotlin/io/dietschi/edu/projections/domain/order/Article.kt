package io.dietschi.edu.projections.domain.order

data class Article(val orderId: String,
                   val articleId: String,
                   val quantity: Int,
                   val description: String) {
}