package patternsForCodingInterviews.modifiedBinarySearch

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744135675_77Unit
object OrderAgnosticBinarySearch {
    @JvmStatic
    fun main(args: Array<String>) {
        println(search(intArrayOf(4, 6, 10), 10))
        println(search(intArrayOf(1, 2, 3, 4, 5, 6, 7), 5))
        println(search(intArrayOf(10, 6, 4), 10))
        println(search(intArrayOf(10, 6, 4), 4))
    }

    /*
    Time: O(LogN)
    Space: O(1)
     */
    fun search(arr: IntArray, key: Int): Int {
        var start = 0
        var end = arr.size - 1
        val isAscendingOrder = arr[start] < arr[end]
        while (start <= end) {
            // calculate the middle of the current range
            val mid = start + (end - start) / 2
            if (key == arr[mid]) return mid
            if (isAscendingOrder) { //Ascending order
                if (key < arr[mid]) {
                    end = mid - 1
                } else {
                    start = mid - 1
                }
            } else { //Descending order
                if (key > arr[mid]) {
                    end = mid - 1
                } else {
                    start = mid + 1
                }
            }
        }
        return 0
    }
}