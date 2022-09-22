package academy.learnprogramming.sorting.bubbleSort

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val intArray = intArrayOf(20, 35, -15, 7, 55, 1, -22)
        //We know that the last index of the array will be sorted.
        //Decrement each time because the end of the array will be sorted
        for (lastUnsortedIndex in intArray.size - 1 downTo 1) {
            for (i in 0 until lastUnsortedIndex) {
                if (intArray[i] > intArray[i + 1]) {
                    swap(intArray, i, i + 1)
                }
            }
        }
        for (i in intArray.indices) {
            println(intArray[i])
        }
    }

    fun swap(array: IntArray, i: Int, j: Int) {
        if (i == j) {
            return
        }
        val temp = array[i]
        array[i] = array[j]
        array[j] = temp
    }
}