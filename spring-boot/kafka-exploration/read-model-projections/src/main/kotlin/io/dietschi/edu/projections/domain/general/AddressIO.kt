package io.dietschi.edu.projections.domain.general

import io.dietschi.edu.projections.schema.avro.AddressChangedEvent

fun AddressChangedEvent.toAddress(): Address {
    val state = this.addressChangedState
    return Address(
        state.street,
        state.houseNumber
    )
}