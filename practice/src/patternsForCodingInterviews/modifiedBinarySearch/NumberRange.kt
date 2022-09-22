package patternsForCodingInterviews.modifiedBinarySearch

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744155307_80Unit
object NumberRange {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = findRange(intArrayOf(4, 6, 6, 6, 9), 6)
        println("Range: [" + result[0] + ", " + result[1] + "]")
        result = findRange(intArrayOf(1, 3, 8, 10, 15), 10)
        println("Range: [" + result[0] + ", " + result[1] + "]")
        result = findRange(intArrayOf(1, 3, 8, 10, 15), 12)
        println("Range: [" + result[0] + ", " + result[1] + "]")
    }

    /*
    Time: O(logN)
    Space: O(1)
     */
    fun findRange(arr: IntArray, key: Int): IntArray {
        val result = intArrayOf(-1, -1)
        result[0] = search(arr, key, false)
        if (result[0] != -1) { // no need to search, if 'key' is not present in the input array
            result[1] = search(arr, key, true)
        }
        return result
    }

    // modified Binary Search
    private fun search(arr: IntArray, key: Int, findMaxIndex: Boolean): Int {
        var keyIndex = -1
        var start = 0
        var end = arr.size - 1
        while (start <= end) {
            val mid = start + (end - start) / 2
            if (key < arr[mid]) {
                end = mid - 1
            } else if (key > arr[mid]) {
                start = mid + 1
            } else { // key == arr[mid]
                keyIndex = mid
                if (findMaxIndex) {
                    start = mid + 1 // search ahead to find the last index of 'key'
                } else {
                    end = mid - 1 // search behind to find the first index of 'key'
                }
            }
        }
        return keyIndex
    }
}