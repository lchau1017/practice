package patternsForCodingInterviews.modifiedBinarySearch

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744190772_84Unit
object _1SearchBitonicArray {
    @JvmStatic
    fun main(args: Array<String>) {
        println(search(intArrayOf(1, 3, 8, 4, 3), 4))
        println(search(intArrayOf(3, 8, 3, 1), 8))
        println(search(intArrayOf(1, 3, 8, 12), 12))
        println(search(intArrayOf(10, 9, 8), 10))
    }

    /*
    Time: O(LogN)
    Space: O(1)
     */
    fun search(arr: IntArray, key: Int): Int {
        val maxIndex = findMax(arr)
        //Now, we can break the array into two sub-arrays:
        //Array from index ‘0’ to maxIndex, sorted in ascending order.
        val keyIndex = binarySearch(arr, key, 0, maxIndex)
        return if (keyIndex != -1) { //Allow us to find the key with the smaller index first
            keyIndex
        } else binarySearch(arr, key, maxIndex + 1, arr.size - 1)
        //Array from index maxIndex+1 to array_length-1, sorted in descending order.
    }

    // order-agnostic binary search
    private fun binarySearch(arr: IntArray, key: Int, start: Int, end: Int): Int {
        var start = start
        var end = end
        while (start <= end) {
            val mid = start + (end - start) / 2
            if (key == arr[mid]) return mid
            if (arr[start] < arr[end]) { //Ascending order
                if (key < arr[mid]) {
                    end = mid - 1
                } else {
                    start = mid + 1
                }
            } else { //Descending order
                if (key > arr[mid]) {
                    end = mid - 1
                } else { // key < arr[mid]
                    start = mid + 1
                }
            }
        }
        return -1
    }

    //Find index of the maximum value in a bitonic array
    private fun findMax(arr: IntArray): Int {
        var start = 0
        var end = arr.size - 1
        while (start < end) {
            val mid = start + (end - start) / 2
            if (arr[mid] > arr[mid + 1]) {
                end = mid
            } else {
                start = mid + 1
            }
        }
        // at the end of the while loop, 'start == end'
        return start
    }
}