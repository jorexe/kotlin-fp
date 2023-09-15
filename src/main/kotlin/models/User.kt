package models

import java.util.Optional

data class User(
    val id: UserId,
    val name: String,
    val addresses: List<Address>,
    val phone: Phone,
)

@JvmInline
value class UserId(val value: Int)

data class Address(
    val street: String,
    val country: String,
    val phone: Optional<Phone>
)

@JvmInline
value class Phone(val value: String)
