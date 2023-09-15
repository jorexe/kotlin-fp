package lib

import arrow.core.Either
import org.junit.jupiter.api.Assertions.assertEquals

fun <L, R> Either<L, R>.assertEqualsLeft(expected: L) =
    assertEquals(expected.asLeft(), this)

fun <L, R> Either<L, R>.assertEqualsRight(expected: R) =
    assertEquals(expected.asRight(), this)
