package academy.learnprogramming.searching.binarySearch

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val intArray = intArrayOf(-22, -15, 1, 7, 20, 35, 55)
        println(iterativeBinarySearch(intArray, -15))
        println(iterativeBinarySearch(intArray, 35))
        println(iterativeBinarySearch(intArray, 8888))
        println(iterativeBinarySearch(intArray, 1))
        println(recursiveBinarySearch(intArray, -15))
        println(recursiveBinarySearch(intArray, 35))
        println(recursiveBinarySearch(intArray, 8888))
        println(recursiveBinarySearch(intArray, 1))
    }

    fun iterativeBinarySearch(input: IntArray, value: Int): Int {
        var start = 0
        var end = input.size
        while (start < end) {
            val midpoint = (start + end) / 2
            println("midpoint = $midpoint")
            if (input[midpoint] == value) {
                return midpoint
            } else if (input[midpoint] < value) {
                start = midpoint + 1
            } else {
                end = midpoint
            }
        }
        return -1
    }

    fun recursiveBinarySearch(input: IntArray, value: Int): Int {
        return recursiveBinarySearch(input, 0, input.size, value)
    }

    fun recursiveBinarySearch(input: IntArray, start: Int, end: Int, value: Int): Int {
        if (start >= end) {
            return -1
        }
        val midpoint = (start + end) / 2
        println("midpoint = $midpoint")
        return if (input[midpoint] == value) {
            midpoint
        } else if (input[midpoint] < value) {
            recursiveBinarySearch(input, midpoint + 1, end, value)
        } else {
            recursiveBinarySearch(input, start, midpoint, value)
        }
    }
}