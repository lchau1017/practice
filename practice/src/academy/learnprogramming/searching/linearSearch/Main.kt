package academy.learnprogramming.searching.linearSearch

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val intArray = intArrayOf(20, 35, -15, 7, 55, 1, -22)
        println(linearSearch(intArray, -15))
        println(linearSearch(intArray, 1))
        println(linearSearch(intArray, 8888))
        println(linearSearch(intArray, -22))
    }

    fun linearSearch(input: IntArray, value: Int): Int {
        for (i in input.indices) {
            if (input[i] == value) {
                return i
            }
        }
        return -1
    }
}