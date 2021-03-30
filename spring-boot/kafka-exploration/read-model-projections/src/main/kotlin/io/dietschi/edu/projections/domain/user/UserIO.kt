package io.dietschi.edu.projections.domain.user

import io.dietschi.edu.projections.domain.general.toAddress
import io.dietschi.edu.projections.schema.avro.AddressChangedEvent
import io.dietschi.edu.projections.schema.avro.UserChangedEvent

fun toUser(personChangedEvent: UserChangedEvent,
           addressChangedEvent: AddressChangedEvent) : User {

    val state = personChangedEvent.userChangedState
    return User(
        personChangedEvent.universalId,
        state.firstName,
        state.lastName,
        addressChangedEvent.toAddress()
    )
}