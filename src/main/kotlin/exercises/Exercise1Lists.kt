package exercises

class Exercise1Lists {
    /**
     * Function that returns a list with increased by 1 numbers
     *
     * [1, 2, 3] -> [2, 3, 4]
     */
    fun increaseByOne(list: List<Int>): List<Int> = list.map { it + 1 }

    /**
     * Function that returns a list with each element squared
     *
     * [1, 2, 3] -> [1, 4, 9]
     */
    fun squared(list: List<Int>): List<Int> = TODO()

    /**
     * Function that increases each value by n
     *
     * [1, 2, 3], 2 -> [3, 4, 5]
     */
    fun increaseByN(list: List<Int>, n: Int): List<Int> = TODO()

    /**
     * Curried: Function that returns a function that increase by N
     *
     * increaseBy3 = increaseByNGenerator(3)
     *
     * [1, 2, 3] -> [4, 5, 6]
     */
    fun increaseByNGenerator(n: Int): (list: List<Int>) -> List<Int> = TODO()

    /**
     * Function that returns each number as string
     *
     * [1, 2, 3] -> ["1", "4", "9"]
     */
    fun asString(list: List<Int>): List<String> = TODO()

    /**
     * Function that filters even values
     *
     * [1, 2, 3] -> [2]
     */
    fun isEven(n: Int): Boolean = n % 2 == 0

    fun evens(list: List<Int>): List<Int> = TODO()

    /**
     * Function that returns a list telling if a number is even or odd
     *
     * [1, 2, 3] -> ["1 is odd", "2 is even", "3 is odd"]
     */
    fun evenOrOdd(list: List<Int>): List<String> = TODO()

    /**
     * Did you create an auxiliary function in the previous exercise?
     *
     * Function that returns a list of each number and its consecutive in the same list
     *
     * [1, 2, 3] -> [1, 2, 2, 3, 3, 4]
     */
    fun consecutives(list: List<Int>): List<Int> = list.flatMap { listOf(it, it + 1) }

    /**
     * Function that receives a list of strings and returns a list with the first and last character of each string
     *
     * ["apple", "orange"] -> ['a', 'e', 'o', 'e']
     */
    fun borders(lists: List<String>): List<Char> = TODO()

    /**
     * Function that flattens a list
     *
     * [[1,2], [3, 4]] -> [1, 2, 3, 4]
     */
    fun <T> flatList(list: List<List<T>>): List<T> = TODO()

    /**
     * Function that returns the size of a list inside a list
     *
     * [[1, 2], [1, 2, 3], [3, 4]]] -> [2, 3, 2]
     */
    fun sizes(lists: List<List<Int>>): List<Int> = TODO()
}
