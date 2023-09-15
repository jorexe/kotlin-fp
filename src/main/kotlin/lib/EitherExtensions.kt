package lib

import arrow.core.Either

fun <T> T.asRight(): Either<Nothing, T> = Either.Right(this)

fun <T> T.asLeft(): Either<T, Nothing> = Either.Left(this)
