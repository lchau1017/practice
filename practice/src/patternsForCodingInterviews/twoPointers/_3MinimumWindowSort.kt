package patternsForCodingInterviews.twoPointers

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743511885_11Unit
object _3MinimumWindowSort {
    @JvmStatic
    fun main(args: Array<String>) {
        println(sort(intArrayOf(1, 2, 5, 3, 7, 10, 9, 12)))
        println(sort(intArrayOf(1, 3, 2, 0, -1, 7, 10)))
        println(sort(intArrayOf(1, 2, 3)))
        println(sort(intArrayOf(3, 2, 1)))
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    fun sort(arr: IntArray): Int {
        var low = 0
        var high = arr.size - 1
        while (low < arr.size - 1 && arr[low] <= arr[low + 1]) {
            low++
        }
        if (low == arr.size - 1) { //The array is sorted
            return 0
        }
        //Find the first number out of sorting from the end
        while (high > 0 && arr[high] >= arr[high - 1]) {
            high--
        }
        //Find the maximum and minimum of the subarray
        var subarrayMax = Int.MIN_VALUE
        var subarrayMin = Int.MAX_VALUE
        for (k in low..high) {
            subarrayMax = Math.max(subarrayMax, arr[k])
            subarrayMin = Math.min(subarrayMin, arr[k])
        }
        //Extend the subarray to include any number which is bigger than the minimum of the subarray
        while (low > 0 && arr[low - 1] > subarrayMin) {
            low--
        }
        while (high < arr.size - 1 && arr[high + 1] < subarrayMax) {
            high++
        }
        return high - low + 1
    }
}