package patternsForCodingInterviews.slidingWindow

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541045705_5Unit
object LongestSubarrayWithOnesAfterReplacement {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            findLength(intArrayOf(0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1), 2)
        )
        println(
            findLength(intArrayOf(0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1), 3)
        )
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    fun findLength(arr: IntArray, k: Int): Int {
        var windowStart = 0
        var maxLength = 0
        var maxOnesCount = 0
        //we’ll iterate through the array to add one number at a time in the window
        for (windowEnd in arr.indices) {
            if (arr[windowEnd] == 1) //we know that we can have a window with 1s repeating maxOnesCount
                maxOnesCount++
            //If we have more than ‘k’ remaining 0s,
            // we should shrink the window as we are not allowed to replace more than ‘k’ 0s.
            //We remove from the window all the 1s. if stil more than k, it means we have
            //more 0s than expected, we shrink the window.
            if (windowEnd - windowStart + 1 - maxOnesCount > k) {
                //We have more than k'Os elements, we shrink the window
                if (arr[windowStart] == 1) {
                    maxOnesCount--
                }
                windowStart++
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1)
        }
        return maxLength
    }
}