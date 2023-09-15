package models

import java.time.Instant
import java.util.Optional

class Order(
    val orderId: OrderId,
    val userId: UserId,
    val date: Instant,
    val address: Address,
    val items: List<Item>,
    val paymentDate: Optional<Instant>
) {

    fun paid() = paymentDate.isPresent

}

@JvmInline
value class OrderId(val value: Int)

data class Item(
    val productId: ProductId,
    val amount: Int
)
