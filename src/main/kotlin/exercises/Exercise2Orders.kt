package exercises

import api.OrderApi
import api.ProductApi
import models.Item
import models.Order
import models.Product
import models.ProductId
import models.User
import models.UserId
import java.time.Instant
import java.time.temporal.ChronoUnit

class Exercise2Orders(val productApi: ProductApi, val orderApi: OrderApi) {

    /**
     * Auxiliary function to have more clarity
     */
    val Item.product: Product get() = productApi.findById(productId).get()

    /**
     * Take a look at the models in the models folder
     *
     * In our first exercise we are going to implement a function that returns pending payment amount for a user, applying
     * certain discounts to an order.
     *
     * Let's start with simple steps
     */

    /**
     * Function that given an order returns if it is paid
     */
    fun Order.paid(): Boolean =
        this.paymentDate.isPresent

    /**
     * Function that returns the total amount of products in an order
     *
     * [(id=1, amount=2), (id=2, amount=5)] -> 7
     */
    fun Iterable<Double>.mySum(): Double = fold(0.0) { acc, n -> acc + n }
    fun Iterable<Int>.mySum(): Int = map { it.toDouble() }.mySum().toInt()
    fun productsAmount(order: Order): Int =
        order.items.map { it.amount }.mySum()

    /**
     * Function that gets the full price of an item
     *
     * (id=1, price=10), (id=2, price=2)
     *
     * (id=1, amount=2) -> 20
     *
     */
    fun getPrice(item: Item): Double =
        item.amount * item.product.price

    /**
     * Function that returns a discount per item. It should work in the following way:
     * - amount <= 4 no discount applied
     * - amount > 4 10% discount in remaining units
     *
     * (id=1, price=10), (id=2, price=2)
     *
     * (id=1, amount=2) -> 0
     * (id=1, amount=6) -> 2 * 10 * 0.1 -> 2
     */
    val MIN_AMOUNT = 4
    val ITEM_DISCOUNT = 0.1
    fun getDiscount(item: Item): Double =
        if (item.amount > MIN_AMOUNT) (item.amount - MIN_AMOUNT) * item.product.price * ITEM_DISCOUNT else 0.0

    /**
     * Function that returns the subtotal amount to pay for an order. Applying the discount per item.
     *
     * (id=1, price=10), (id=2, price=2)
     *
     * [(id=1, amount=6), (id=2, amount=4)] -> 66
     */
    fun subTotal(order: Order): Double =
        order.items.map { getPrice(it) - getDiscount(it) }.mySum()

    /**
     * Function that returns a 20% discount of the subtotal order amount
     *
     * 100 -> 20
     */
    val SUB_TOTAL_DISCOUNT = 0.2
    fun getSubTotalDiscount(subTotal: Double): Double =
        subTotal * SUB_TOTAL_DISCOUNT

    /**
     * Function that given an userId returns the total amount of pending payment
     */
    fun pendingUserPayment(userId: UserId): Double =
        orderApi.findByUser(userId)
            .filter { !it.paid() }
            .map { subTotal(it) }
            .map { it - getSubTotalDiscount(it) }
            .mySum()

    /**
     * Try to change previous function to apply subTotalDiscount only if order was created a month ago
     */
    fun appliesDiscount(order: Order): Boolean =
        order.paymentDate
            .map { date -> Instant.now().minus(1, ChronoUnit.MONTHS).isBefore(date) }
            .orElse(false)

    fun subTotalWithDiscount(order: Order): Double = subTotal(order).let { subTotal ->
        if (appliesDiscount(order))
            subTotal - getSubTotalDiscount(subTotal)
        else
            subTotal
    }

    fun pendingUserPaymentConditionalDiscount(userId: UserId): Double =
        orderApi.findByUser(userId)
            .filter { !it.paid() }
            .map { subTotalWithDiscount(it) }
            .mySum()
}

