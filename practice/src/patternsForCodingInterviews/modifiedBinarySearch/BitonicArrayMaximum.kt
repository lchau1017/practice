package patternsForCodingInterviews.modifiedBinarySearch

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744182053_83Unit
object BitonicArrayMaximum {
    @JvmStatic
    fun main(args: Array<String>) {
        println(findMax(intArrayOf(1, 3, 8, 12, 4, 2)))
        println(findMax(intArrayOf(3, 8, 3, 1)))
        println(findMax(intArrayOf(1, 3, 8, 12)))
        println(findMax(intArrayOf(10, 9, 8)))
    }

    /*
    Template #2 src/algorithms/binarySearch/templates/Template2.java
    Time: O(logN)
    Space: O(1)
     */
    fun findMax(arr: IntArray): Int {
        var start = 0
        var end = arr.size - 1
        while (start < end) {
            val mid = start + (end - start) / 2
            if (arr[mid] > arr[mid + 1]) { // we are in the second (descending) part of the bitonic array
                //Therefore, our required number could either be pointed out by middle or will be before middle
                end = mid
            } else { // arr[middle] < arr[middle + 1]
                //we are in the first (ascending) part of the bitonic array
                //Therefore, the required number will be after middle
                start = mid + 1
            }
        }
        // at the end of the while loop, 'start == end'
        return arr[start]
    }
}