package patternsForCodingInterviews.slidingWindow

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1627871358579_1Unit
object MaximumSumSubarrayOfSizeK {
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(2, 1, 5, 1, 3, 2)
        val k = 3
        //Output 9
        //Explanation: Subarray with maximum sum is [5, 1, 3].
        //System.out.println(findMaxSumSubArrayBrutForce(k, nums));
        println(findMaxSumSubArraySlidingWindow(k, nums))
    }

    //SLIDING WINDOW
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    fun findMaxSumSubArraySlidingWindow(k: Int, arr: IntArray): Int {
        var windowSum = 0
        var maxSum = 0
        var windowStart = 0
        for (windowEnd in arr.indices) {
            windowSum += arr[windowEnd]
            if (windowEnd >= k - 1) {
                maxSum = Math.max(maxSum, windowSum)
                windowSum -= arr[windowStart] //Substract element going out
                windowStart++ // Slide the window ahead
            }
        }
        return maxSum
    }

    //Brut force
    fun findMaxSumSubArrayBrutForce(k: Int, arr: IntArray): Int {
        var maxSum = 0
        var windowSum: Int
        for (i in 0..arr.size - k) {
            windowSum = 0
            for (j in i until i + k) {
                windowSum += arr[j]
            }
            maxSum = Math.max(maxSum, windowSum)
        }
        return maxSum
    }
}