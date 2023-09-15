package exercises

import api.TestOrderApi
import api.TestProductApi
import models.Item
import models.Order
import models.OrderId
import models.ProductId
import models.UserId
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Exercise2OrdersTest {
    private val target = Exercise2Orders(TestProductApi, TestOrderApi)

    private fun order(id: Int): Order = TestOrderApi.findById(OrderId(id)).get()

    @Test
    fun `productsAmount tests`() {
        assertEquals(6, target.productsAmount(order(1)))
        assertEquals(10, target.productsAmount(order(2)))
        assertEquals(3, target.productsAmount(order(3)))
        assertEquals(2, target.productsAmount(order(4)))
        assertEquals(0, target.productsAmount(order(5)))
    }

    @Test
    fun `getPrice tests`() {
        assertEquals(20.0, target.getPrice(Item(ProductId(1), 2)))
        assertEquals(6.0, target.getPrice(Item(ProductId(2), 3)))
        assertEquals(3.0, target.getPrice(Item(ProductId(3), 1)))
    }

    @Test
    fun `getDiscount tests`() {
        assertEquals(2.0, target.getDiscount(Item(ProductId(1), 6)))
        assertEquals(0.0, target.getDiscount(Item(ProductId(2), 3)))
        assertEquals(1.8, target.getDiscount(Item(ProductId(3), 10)))
    }

    @Test
    fun `subTotal tests`() {
        assertEquals(38.0, target.subTotal(order(1)))
        assertEquals(48.25, target.subTotal(order(2)))
        assertEquals(30.0, target.subTotal(order(3)))
        assertEquals(5.0, target.subTotal(order(4)))
        assertEquals(0.0, target.subTotal(order(5)))
    }

    @Test
    fun `getSubTotal discount tests`() {
        assertEquals(1.0, target.getSubTotalDiscount(5.0))
        assertEquals(4.0, target.getSubTotalDiscount(20.0))
        assertEquals(0.0, target.getSubTotalDiscount(0.0))
    }

    @Test
    fun `pendingUserPayment tests`() {
        assertEquals(72.56, target.pendingUserPayment(UserId(1)))
        assertEquals(38.6, target.pendingUserPayment(UserId(2)))
        assertEquals(28.8, target.pendingUserPayment(UserId(3)))
        assertEquals(0.0, target.pendingUserPayment(UserId(4)))
    }
}
