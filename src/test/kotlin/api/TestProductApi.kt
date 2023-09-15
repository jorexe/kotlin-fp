package api

import lib.toOptional
import models.Product
import models.ProductId
import java.util.Optional

object TestProductApi: ProductApi {

    private val products = mapOf(
        1 to Product(ProductId(1), "Watermelon", 10.0),
        2 to Product(ProductId(2), "Apple", 2.0),
        3 to Product(ProductId(3), "Orange", 3.0),
        4 to Product(ProductId(4), "Banana", 2.5)
    )

    override fun findById(id: ProductId): Optional<Product> {
        return products[id.value].toOptional()
    }
}
