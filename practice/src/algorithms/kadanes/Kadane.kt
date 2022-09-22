package algorithms.kadanes

//https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
object Kadane {
    @JvmStatic
    fun main(args: Array<String>) {
        val a = intArrayOf(-2, -3, 4, -1, -2, 1, 5, -3)
        println(maxSubArraySum(a))
        maxSubArraySumPrintSubarray(a, a.size)
    }

    /*
    Time : O(n)
    space: O(1)
     */
    fun maxSubArraySum(a: IntArray): Int {
        val size = a.size
        var max_so_far = Int.MIN_VALUE
        var max_ending_here = 0
        for (i in 0 until size) {
            max_ending_here = max_ending_here + a[i]
            if (max_so_far < max_ending_here) max_so_far = max_ending_here
            if (max_ending_here < 0) max_ending_here = 0
        }
        return max_so_far
    }

    //To print the subarray with the maximum sum,
    fun maxSubArraySumPrintSubarray(a: IntArray, size: Int) {
        var max_so_far = Int.MIN_VALUE
        var max_ending_here = 0
        var start = 0
        var end = 0
        var s = 0
        for (i in 0 until size) {
            max_ending_here += a[i]
            if (max_so_far < max_ending_here) {
                max_so_far = max_ending_here
                start = s
                end = i
            }
            if (max_ending_here < 0) {
                max_ending_here = 0
                s = i + 1
            }
        }
        println(
            "Maximum contiguous sum is "
                    + max_so_far
        )
        println("Starting index $start")
        println("Ending index $end")
    }
}