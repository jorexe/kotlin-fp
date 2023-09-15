package api

import models.Product
import models.ProductId
import models.User
import models.UserId
import java.util.Optional

interface ProductApi {

    fun findById(id: ProductId): Optional<Product>

}
