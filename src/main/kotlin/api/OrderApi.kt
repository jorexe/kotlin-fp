package api

import models.Order
import models.OrderId
import models.UserId
import java.util.Optional

interface OrderApi {

    fun findById(orderId: OrderId): Optional<Order>

    fun findByUser(userId: UserId): List<Order>

}
