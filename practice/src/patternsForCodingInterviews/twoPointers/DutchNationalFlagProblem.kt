package patternsForCodingInterviews.twoPointers

////https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743488620_8Unit
object DutchNationalFlagProblem {
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(1, 0, 2, 1, 0)
        sort(arr)
        for (num in arr) print("$num ")
        println()
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    fun sort(arr: IntArray) {
        var low = 0
        var high = arr.size - 1
        var i = 0
        while (i <= high) {
            if (arr[i] == 0) {
                swap(arr, i, low)
                i++
                low++
            } else if (arr[i] == 1) {
                i++
            } else { //The case for arr[i] == 2
                swap(arr, i, high)
                high--
            }
        }
    }

    private fun swap(arr: IntArray, i: Int, j: Int) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }
}