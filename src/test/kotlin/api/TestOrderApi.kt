package api

import lib.toOptional
import models.Address
import models.Item
import models.Order
import models.OrderId
import models.Phone
import models.ProductId
import models.UserId
import java.time.Instant
import java.util.Optional

object TestOrderApi: OrderApi {

    override fun findById(orderId: OrderId): Optional<Order> {
        return orders[orderId.value].toOptional()
    }

    override fun findByUser(userId: UserId): List<Order> {
        return orders.values.filter { it.userId == userId }
    }

    private val orders = mapOf(
        1 to Order(
            OrderId(1),
            UserId(1),
            Instant.now(),
            Address("1885 Crist Islands", "Tuvalu", Phone("671-276-0870").toOptional()),
            listOf(
                Item(ProductId(1), 3),
                Item(ProductId(2), 1),
                Item(ProductId(3), 2),
            ),
            Instant.now().toOptional()
        ),
        2 to Order(
            OrderId(2),
            UserId(2),
            Instant.now(),
            Address("29398 Winnifred Village", "Sudan", Phone("229-960-4239").toOptional()),
            listOf(
                Item(ProductId(1), 3),
                Item(ProductId(3), 2),
                Item(ProductId(4), 5),
            ),
            Optional.empty()
        ),
        3 to Order(
            OrderId(3),
            UserId(2),
            Instant.now(),
            Address("29398 Winnifred Village", "Sudan", Phone("229-960-4239").toOptional()),
            listOf(
                Item(ProductId(1), 3),
            ),
            Instant.now().toOptional()
        ),
        4 to Order(
            OrderId(4),
            UserId(3),
            Instant.now(),
            Address("09591 Emerald Spur", "Pitcairn Islands", Phone("236-344-9949").toOptional()),
            listOf(
                Item(ProductId(4), 2),
            ),
            Instant.now().toOptional()
        ),
        5 to Order(
            OrderId(5),
            UserId(2),
            Instant.now(),
            Address("29398 Winnifred Village", "Sudan", Phone("229-960-4239").toOptional()),
            listOf(),
            Optional.empty()
        ),
        6 to Order(
            OrderId(6),
            UserId(1),
            Instant.now(),
            Address("1885 Crist Islands", "Tuvalu", Phone("671-276-0870").toOptional()),
            listOf(
                Item(ProductId(1), 1),
                Item(ProductId(3), 5),
                Item(ProductId(2), 9),
            ),
            Optional.empty()
        ),
        7 to Order(
            OrderId(7),
            UserId(1),
            Instant.now(),
            Address("1885 Crist Islands", "Tuvalu", Phone("671-276-0870").toOptional()),
            listOf(),
            Optional.empty()
        ),
        8 to Order(
            OrderId(8),
            UserId(1),
            Instant.now(),
            Address("1885 Crist Islands", "Tuvalu", Phone("671-276-0870").toOptional()),
            listOf(
                Item(ProductId(1), 3),
                Item(ProductId(4), 8),
            ),
            Optional.empty()
        ),
        9 to Order(
            OrderId(9),
            UserId(3),
            Instant.now(),
            Address("09591 Emerald Spur", "Pitcairn Islands", Phone("236-344-9949").toOptional()),
            listOf(
                Item(ProductId(1), 3),
                Item(ProductId(2), 3),
            ),
            Optional.empty()
        ),
        10 to Order(
            OrderId(10),
            UserId(1),
            Instant.now(),
            Address("1885 Crist Islands", "Tuvalu", Phone("671-276-0870").toOptional()),
            listOf(),
            Optional.empty()
        ),
    )
}
