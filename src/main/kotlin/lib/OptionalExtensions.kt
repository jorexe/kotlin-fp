package lib

import java.util.Optional

fun <T> T.toOptional(): Optional<T & Any> = if (this == null) Optional.empty() else Optional.of(this)
