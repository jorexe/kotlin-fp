package exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Exercise1ListsTest {

    private val target = Exercise1Lists()

    @Test
    fun `increaseByOne tests`() {
        assertEquals(listOf(2, 3, 4), target.increaseByOne(listOf(1, 2, 3)))
        assertEquals(listOf(0, -1, -2), target.increaseByOne(listOf(-1, -2, -3)))
    }

    @Test
    fun `squared tests`() {
        assertEquals(listOf(1, 4, 9), target.squared(listOf(1, 2, 3)))
        assertEquals(listOf(1, 4, 9), target.squared(listOf(-1, -2, -3)))
    }

    @Test
    fun `increaseByN tests`() {
        assertEquals(listOf(6, 7, 8), target.increaseByN(listOf(1, 2, 3), 5))
        assertEquals(listOf(1, 0, -1), target.increaseByN(listOf(-1, -2, -3), 2))
    }

    @Test
    fun `increaseByNGenerator tests`() {
        val increaseBy4 = target.increaseByNGenerator(4)

        assertEquals(listOf(5, 6, 7), increaseBy4(listOf(1, 2, 3)))
        assertEquals(listOf(3, 2, 1), increaseBy4(listOf(-1, -2, -3)))
    }

    @Test
    fun `asString tests`() {
        assertEquals(listOf("5", "6", "7"), target.asString(listOf(1, 2, 3)))
        assertEquals(listOf("3", "2", "1"), target.asString(listOf(-1, -2, -3)))
    }

    @Test
    fun `evens tests`() {
        assertEquals(listOf(2), target.evens(listOf(1, 2, 3)))
        assertEquals(listOf(-2), target.evens(listOf(-1, -2, -3)))
    }

    @Test
    fun `evenOrOdd tests`() {
        assertEquals(listOf("1 is odd", "2 is even", "3 is odd"), target.evenOrOdd(listOf(1, 2, 3)))
        assertEquals(listOf("-1 is odd", "-2 is even", "-3 is odd"), target.evenOrOdd(listOf(-1, -2, -3)))
    }

    @Test
    fun `consecutives tests`() {
        assertEquals(listOf(1, 2, 2, 3, 3, 4), target.consecutives(listOf(1, 2, 3)))
        assertEquals(listOf(-1, 0, -2, -1, -3, -2), target.consecutives(listOf(-1, -2, -3)))
    }

    @Test
    fun `borders tests`() {
        assertEquals(listOf('a', 'e', 'o', 'e'), target.borders(listOf("apple", "orange")))
        assertEquals(listOf('d', 'g', 'c', 't'), target.borders(listOf("dog", "cat")))
    }

    @Test
    fun `flatList tests`() {
        assertEquals(listOf(1, 2, 3, 4), target.flatList(listOf(listOf(1, 2), listOf(3), listOf(4))))
        assertEquals(listOf(1, 7), target.flatList(listOf(listOf(1), listOf(7))))
    }

    @Test
    fun `sizes tests`() {
        assertEquals(listOf(2, 1, 1), target.sizes(listOf(listOf(1, 2), listOf(3), listOf(4))))
        assertEquals(listOf(1, 1), target.sizes(listOf(listOf(1), listOf(7))))
    }
}
