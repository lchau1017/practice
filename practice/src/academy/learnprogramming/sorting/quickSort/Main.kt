package academy.learnprogramming.sorting.quickSort

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val intArray = intArrayOf(20, 35, -15, 7, 55, 1, -22)
        quickSort(intArray, 0, intArray.size)
        for (i in intArray.indices) {
            print(intArray[i].toString() + ", ")
        }
    }

    /*
    The average time complexity of quick sort is O(N log(N)).
    The derivation is based on the following notation: T(N) = Time Complexity of Quick Sort for input of size N.
    At each step, the input of size N is broken into two parts say J and N-J.
    Space complexity: O(1)
     */
    fun quickSort(input: IntArray, start: Int, end: Int) {
        if (end - start < 2) { //If it remains 2 elements to sort, stop here
            return
        }
        val pivotIndex = partition(input, start, end)
        quickSort(input, start, pivotIndex)
        for (i in input.indices) {
            println(input[i])
        }
        quickSort(input, pivotIndex + 1, end)
    }

    fun partition(input: IntArray, start: Int, end: Int): Int {
        // This is using the first element as the pivot
        val pivot = input[start]
        var i = start
        var j = end
        while (i < j) {
            // NOTE: empty loop body
            while (i < j && input[--j] >= pivot) {
                //Body is empty
            }
            if (i < j) {
                input[i] = input[j]
            }
            // NOTE: empty loop body
            while (i < j && input[++i] <= pivot) {
                //Body is empty
            }
            if (i < j) {
                input[j] = input[i]
            }
        }
        input[j] = pivot
        return j
    }
}