package exercises

import arrow.core.Either
import arrow.core.Either.Companion.zipOrAccumulate
import arrow.core.flatMap
import arrow.core.zip
import lib.asLeft
import lib.asRight
import lib.toOptional
import models.Address
import models.Phone
import kotlin.jvm.optionals.getOrNull

class Exercise3Either {

    /**
     * Either type is a monad that can contain two different values,
     * one in case of success or another one in case of error
     *
     * By Convention:
     * - Right on success
     * - Left on failure
     *
     * Either is Right biased, this means when its value is a Left, all following computations won't be done
     */
    fun example(): Unit {
        val success: Either<Int, Int> = 2.asRight()
        val failure: Either<Int, Int> = 2.asLeft()

        success.map { it * 2 } // Right(4)
        failure.map { it * 2 } // Left(2)

        // It is not necessary for the two types to be the same
        val intSuccess: Either<String, Int> = 2.asRight()
        val strFailure: Either<String, Int> = "Error!".asLeft()

    }

    // And a common pattern is to define an error class to be able to handle them more accurately
    sealed class SampleFailure
    data class SomeSampleFailure(val code: Int) : SampleFailure()
    data object AnotherSampleFailure : SampleFailure()

    val eitherFailure: Either<SampleFailure, Int> = SomeSampleFailure(2).asLeft()

    /**
     * Let's start: Create a function that given a nullable object returns an either with the object or an error
     */
    object NullObjectFound

    fun <T> eitherValueOrNullObjectFound(t: T?): Either<NullObjectFound, T> =
        TODO()

    /**
     * Now try to implement same function, but receiving the error as parameter. Even better if it is using a consumer
     */
    //fun eitherValueOrError() = TODO()

    /**
     * Write a function that given an Address returns its street
     */
    fun getStreet(address: Either<NullObjectFound, Address>): Either<NullObjectFound, String> =
        TODO()

    /**
     * Write a function that given an Address returns its phone
     *
     * Hint: You can use eitherValueOrNullObjectFound
     */
    fun getPhone(address: Either<NullObjectFound, Address>): Either<NullObjectFound, Phone> =
        TODO()

    /**
     * Take a look at this myMap implementation, please make sure you understand the signature and the generic types
     */
    fun <L, R, R2> myMap(either: Either<L, R>, f: (R) -> R2): Either<L, R2> = when (either) {
        is Either.Left -> either
        is Either.Right -> f(either.value).asRight()
    }

    /**
     * Write myFlatMap function and its signature
     */
    // TODO()

    /**
     * Write myMap2 in terms of myFlatMap
     */
    fun <L, R, R2> myMap2(either: Either<L, R>, f: (R) -> R2): Either<L, R2> =
        TODO()

    /**
     * Write myMapLeft function and its signature
     */
    // TODO()

    /**
     * Either type has other utility functions, some of them will help us to combine multiple eithers
     * like zip and zipOrAccumulate
     */

    // Zip will return either the computation or the first error
    val zipSum = 2.asRight().zip(3.asRight()) { a, b -> a + b } // Right(5)
    val zipError = 2.asRight().zip(3.asLeft()) { a: Int, b: Int -> a + b } // Left(3)

    // zipOrAccumulate will return either the computation or a list of errors
    val zipOrAccumulateSum: Either<List<String>, Int> =
        zipOrAccumulate(2.asRight(), 3.asRight()) { a, b -> a + b } // Right(5)
    val zipOrAccumulateError: Either<List<String>, Int> =
        zipOrAccumulate("e1".asLeft(), 3.asRight(), "e2".asLeft()) { a: Int, b: Int, c: Int -> a + b + c } // Left(3)

    /**
     * Create a function that given a list of nullable fields, returns either an Address or a list of invalid fields
     *
     * Note: An address without phone is valid
     */
    data class InvalidField(val field: String)

    fun createAddress(street: String?, country: String?, phone: Phone?): Either<List<InvalidField>, Address> =
        TODO()
}
