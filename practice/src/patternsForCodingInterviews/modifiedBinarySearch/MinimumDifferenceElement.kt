package patternsForCodingInterviews.modifiedBinarySearch

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744174253_82Unit
object MinimumDifferenceElement {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            searchMinDiffElement(intArrayOf(4, 6, 10), 7)
        )
        println(
            searchMinDiffElement(intArrayOf(4, 6, 10), 4)
        )
        println(
            searchMinDiffElement(intArrayOf(1, 3, 8, 10, 15), 12)
        )
        println(
            searchMinDiffElement(intArrayOf(4, 6, 10), 17)
        )
    }

    /*
    Time: O(logN)
    Space: O(1)
     */
    fun searchMinDiffElement(arr: IntArray, key: Int): Int {
        if (key < arr[0]) return arr[0]
        if (key > arr[arr.size - 1]) return arr[arr.size - 1]
        var start = 0
        var end = arr.size - 1
        while (start <= end) {
            val mid = start + (end - start) / 2
            if (key < arr[mid]) {
                end = mid - 1
            } else if (key > arr[mid]) {
                start = mid + 1
            } else { //If key == arr[mid]
                return arr[mid]
            }
        }
        // at the end of the while loop, 'start == end+1'
        // we are not able to find the element in the given array
        // return the element which is closest to the 'key'
        return if (arr[start] - key < key - arr[end]) {
            arr[start]
        } else arr[end]
    }
}