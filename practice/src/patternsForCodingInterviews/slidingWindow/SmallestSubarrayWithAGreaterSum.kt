package patternsForCodingInterviews.slidingWindow

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628540999042_0Unit
object SmallestSubarrayWithAGreaterSum {
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(2, 1, 5, 2, 3, 2)
        val S = 7
        //Output 2
        //Explanation: The smallest subarray with a sum greater than or equal to '7' is [5, 2].
        //FIND THE SMALLEST SUBARRAY (not sum of smallest subarray)
        println(findMinSubArray(S, nums))
    }

    //SLIDING WINDOW
    /*
    Time complexity: O(N), for loop runs for all elements.
    inner while loop, process each element only once.
    Therefore, O(N + N) which is equivalent to O(N)
    Space complexity: O(1)
     */
    fun findMinSubArray(S: Int, arr: IntArray): Int {
        var windowSum = 0
        var windowStart = 0
        var minLength = Int.MAX_VALUE
        for (windowEnd in arr.indices) {
            windowSum += arr[windowEnd]
            //shrink the window as small as possible until the 'windowSum' is smaller than 'S'
            while (windowSum >= S) {
                minLength = Math.min(minLength, windowEnd - windowStart + 1)
                windowSum -= arr[windowStart] //Substract element going out
                windowStart++ // slide the window ahead
            }
        }
        return if (minLength == Int.MAX_VALUE) 0 else minLength
    }
}