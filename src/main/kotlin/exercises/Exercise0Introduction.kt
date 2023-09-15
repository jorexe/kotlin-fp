package exercises

import arrow.core.Either
import models.Address
import models.Order
import models.OrderId
import models.Phone
import models.Product
import models.User
import models.UserId
import java.util.Optional
import java.util.concurrent.CompletableFuture

/**
 * Functional Programming: Paradigm where programs are constructed by applying and composing functions.
 * It tries to avoid shared states, mutability and side effects.
 *
 * Here are some of the main concepts, feel free to look for more
 * information in our source of truth (internet) or a book :)
 */

/**
 * Immutability
 */
fun immutability() {
    val optional = Optional.of(5)
    val newOptional = optional.map { n -> n + 1 }

    println(optional) // Prints Optional(5)
    println(newOptional) // Prints Optional(6)

    val list = listOf(2, 3)
    val squared = list.map { it * it }

    println(list) // Prints [2, 3]
    println(squared) // Prints [4, 9]
}

/**
 * Referential transparency
 *
 * This means if we replace an expression by its result we are going to have the same value.
 * This concept applies to pure functions, functions that do not have side effects
 */
fun referentialTransparency() {
    // Pure function
    fun byOne(n: Int): Int = n + 1

    // Following expressions are equivalents, and referential transparent since we are
    // using only pure functions (byOne, *)
    byOne(2) * byOne(3)
    3 * byOne(3)
    3 * 4
    12

    // Impure function
    fun byTwo(n: Int): Int {
        println("Side Effect!")
        return n + 2
    }

    // Following expressions are not equivalents
    byOne(2) * byOne(3)
    3 * byOne(3)
    3 * 4
}

/**
 * High order functions
 *
 * A high order function is a function that receives a function as a parameter, returns a function, or both
 *
 * Tip: Always try to keep in mind the typing of the values/functions, this will help you considerable when you are
 * working with high order functions
 */
fun highOrderFunctions() {
    // High order function that receives a function
    fun applyToFive(f: (Int) -> Int): Int = f(5)

    val afterApply: Int = applyToFive { x -> x - 1 }
    println(afterApply) // Prints 4

    // High order function that returns a function
    fun multiplyBy(n: Int): (Int) -> Int = { x -> x * n }

    val multiplyBy2: (Int) -> Int = multiplyBy(2)
    println(multiplyBy2(4)) // Prints 8

    // High order function that receives and return a function
    fun fogInt(f: (Int) -> (Int), g: (Int) -> Int): (Int) -> Int = { x -> f(g(x)) }

    val composed = fogInt(multiplyBy2) { it + 1 }
    println(composed(5)) // Prints 12

    // And if we want to do it in a generic way
    // Check the types of everything
    fun <A, B, C> fog(
        f: (B) -> C,
        g: (A) -> B
    ): (A) -> C = { f(g(it)) }

    // Then, if we want to combine different functions with different, the compiler will help us
    val productToPrice: (Product) -> Double = { it.price }
    val priceToString: (Double) -> String = { "$" + String.format("%.2f", it) }

    // How would you join the blocks?
    fun renderProductPrice(product: Product): String = TODO()

    // Now you can try to implement gof with the proper typing

}

/**
 * Monads and common functions
 *
 * A monad is a way to sequentiate instructions in functional programming. If you are brave you can google it
 * But in an easier way is a type inside a context
 *
 * Common monads are List, Optional, Either, Future
 *
 * A monad M has to implement following methods and obey some laws:
 * unit(x: X) = M(x) -> listOf, Optional::of, Either::right
 * flatMap(f: a -> M(b)) = M(b)
 *
 * Note: map function can be implemented using flatmap and unit
 *
 * But it is easier to see them in practice, isn't it?
 */
fun monadsAndCommonFunctions() {
    val aList = listOf(2, 3)

    // We saw this before
    aList.map { n -> n + 1 } // [3, 4]

    // And the new one
    aList.flatMap { n -> listOf(n, n + 1) } // [2, 3, 3, 4]

    // But why is this s.. powerful?
    // Well, because you can apply functions to your domain model independent of the context you are
    val coolTransformationFunction: (Int) -> String = { it.toString() }

    val list = listOf(1)
    val optional = Optional.of(1)
    val either = Either.Right(1)
    val future = Future.completedFuture(1)

    list.map(coolTransformationFunction) // ["1"]
    optional.map(coolTransformationFunction) // Optional("1")
    either.map(coolTransformationFunction) // Right("1")
    future.map(coolTransformationFunction) // Future("1"), yes they change the names sometimes

    // Common functions (not for every monad)

    /**
     * map(f: A -> B): M<B>
     *
     * - applies a f: A -> B to every element inside a M<A> and returns M<B>
     * - returns always the same amount of elements
     */
    listOf(1, 2, 3, 4).map { x -> x * x }
    listOf(1, 2, 3, 4).map { x -> x.toString() }
    Optional.of(3).map { x -> x * x }

    /** flatMap(f: A -> M<B>): M<B>
     *
     * - applies a f: A -> M<B> and returns M<B>
     * - can return any amount elements
     */
    aList.flatMap { n -> listOf(n, n + 1) } // [2, 3, 3, 4]

    // With futures
    fun getOrder(id: OrderId): Future<Order> = TODO()
    fun getUser(id: UserId): Future<User> = TODO()

    val orderUser: Future<User> = getOrder(OrderId(1)).flatMap { order -> getUser(order.userId) }

    // With optionals
    val optionalAddress: Optional<Address> = Optional.empty()
    val phone: Optional<Phone> = optionalAddress.flatMap { it.phone }

    // Yes, you can also combine monads, but let's not get things more complicated

    /**
     * filter(f: A -> Boolean): M<A>
     *
     * - applies a f: A -> Boolean and get the values that returns true when the function applied
     * - less elements than before
     */
    listOf(1, 2, 3, 4).filter { it % 2 == 0 } // [2, 4]
    Optional.of(3).filter { it % 2 == 0 } // Empty

    /**
     * fold(acc: Z, (Z, A) -> Z): Z
     *
     * - apply the provided operation sequentially to the elements and returns the accumulated value
     * - it works the same as reduce, but provides a default value (it is on you)
     * - returns a reduction of all the values
     *
     * More info on fold/reduce https://kotlinlang.org/docs/collection-aggregate.html#fold-and-reduce
     */
    listOf(1, 2, 3, 4).fold(0) { acc, x -> acc + x } // Sum of all elements
    // Let's break this
    // acc=0, x=1 -> Remaining [2, 3, 4]
    // acc=1, x=2 -> Remaining [3, 4]
    // acc=3, x=3 -> Remaining [4]
    // acc=6, x=4 -> Remaining []
    // acc = 10 -> returns

    // The type of the accumulator does not neccesary have the same value
    listOf(1, 2, 3, 4).fold("") { acc, x -> acc + x + "," } // "1,2,3,4,"
}

/**
 * Renaming completable future to make it easier
 */
typealias Future<T> = CompletableFuture<T>

fun <A, B> Future<A>.map(f: (A) -> B): Future<B> = thenApply(f)
fun <A, B> Future<A>.flatMap(f: (A) -> Future<B>): Future<B> = thenCompose(f)
