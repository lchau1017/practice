package academy.learnprogramming.sorting.selectionSort

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val intArray = intArrayOf(20, 35, -15, 7, 55, 1, -22)
        //Get the largest element in the array and swap it to the latest index
        //Decrement latest index
        for (lastUnsortedIndex in intArray.size - 1 downTo 1) {
            //Largest start always at index 0, that's why i in the next loop start at index 1
            var largest = 0
            for (i in 1..lastUnsortedIndex) {
                if (intArray[i] > intArray[largest]) {
                    largest = i
                }
            }
            swap(intArray, largest, lastUnsortedIndex)
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