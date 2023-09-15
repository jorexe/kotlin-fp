package models

data class Product(
    val id: ProductId,
    val name: String,
    val price: Double,
)

@JvmInline
value class ProductId(val value: Int)
