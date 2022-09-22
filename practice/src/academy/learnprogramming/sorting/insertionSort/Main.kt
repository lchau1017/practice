package academy.learnprogramming.sorting.insertionSort

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val intArray = intArrayOf(20, 35, -15, 7, 55, 1, -22)
        for (firstUnsortedIndex in 1 until intArray.size) {
            val newElement = intArray[firstUnsortedIndex]
            var i: Int
            i = firstUnsortedIndex
            while (i > 0 && intArray[i - 1] > newElement) {
                intArray[i] = intArray[i - 1]
                i--
            }
            intArray[i] = newElement
        }
        for (i in intArray.indices) {
            println(intArray[i])
        }
    }
}