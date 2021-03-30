package io.dietschi.edu.projections.domain.user

import io.dietschi.edu.projections.domain.general.Address
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(
    collection = "users"
)
data class User(@Id val universalId: String,
                val firstName : String,
                val lastName: String,
                val address: Address,
                val createdDate: LocalDateTime = LocalDateTime.now()
) {
}