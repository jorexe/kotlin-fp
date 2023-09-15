package exercises

import lib.asLeft
import lib.asRight
import lib.assertEqualsLeft
import lib.assertEqualsRight
import lib.toOptional
import models.Address
import models.Phone
import org.junit.jupiter.api.Test
import java.util.Optional

class Exercise3EitherTest {

    private val target = Exercise3Either()

    private val nullObjectFound = Exercise3Either.NullObjectFound
    private fun invalidField(field: String) = Exercise3Either.InvalidField(field)

    @Test
    fun `eitherValueOrNullObjectFound tests`() {
        target.eitherValueOrNullObjectFound(2).assertEqualsRight(2)
        target.eitherValueOrNullObjectFound(null).assertEqualsLeft(nullObjectFound)
    }

    @Test
    fun `eitherValueOrError tests`() {
        TODO("Writing this tests are part of the exercise")
    }

    @Test
    fun `getStreet tests`() {
        target.getStreet(Address("1885 Crist Islands", "Tuvalu", Phone("671-276-0870").toOptional()).asRight())
            .assertEqualsRight("1885 Crist Islands")
        target.getStreet(nullObjectFound.asLeft())
            .assertEqualsLeft(nullObjectFound.asLeft())
    }

    @Test
    fun `getPhone tests`() {
        target.getPhone(Address("1885 Crist Islands", "Tuvalu", Phone("671-276-0870").toOptional()).asRight())
            .assertEqualsRight("671-276-0870")
        target.getPhone(Address("1885 Crist Islands", "Tuvalu", Optional.empty()).asRight())
            .assertEqualsLeft(nullObjectFound.asLeft())
        target.getPhone(nullObjectFound.asLeft())
            .assertEqualsLeft(nullObjectFound.asLeft())
    }

    @Test
    fun `myFlatMap tests`() {
        TODO("Writing this tests are part of the exercise")
    }

    @Test
    fun `myMap2 tests`() {
        TODO("Writing this tests are part of the exercise")
    }

    @Test
    fun `myMapLeft tests`() {
        TODO("Writing this tests are part of the exercise")
    }

    fun invalidFields(vararg field: String) = field.map { Exercise3Either.InvalidField(it) }

    @Test
    fun `createAddress tests`() {
        target.createAddress(null, null, null).assertEqualsLeft(invalidFields("street", "country"))
        target.createAddress("street", null, null).assertEqualsLeft(invalidFields("country"))
        target.createAddress(null, "country", Phone("1234")).assertEqualsLeft(invalidFields("street"))
        target.createAddress("street", "country", null).assertEqualsRight(Address("street", "country", Optional.empty()))
        target.createAddress("street", "country", Phone("1234")).assertEqualsRight(Address("street", "country", Phone("1234").toOptional()))
    }
}
