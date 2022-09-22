package algorithms.dynamicProgramming.strategicApproachToDP

//https://leetcode.com/explore/learn/card/dynamic-programming/631/strategy-for-solving-dp-problems/4100/
object MaximumScoreFromPerformingMultiplicationOperations {
    /*
    You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m.
    The arrays are 1-indexed.

    You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed),
    you will:

    Choose one integer x from either the start or the end of the array nums.
    Add multipliers[i] * x to your score.
    Remove x from the array nums.
    Return the maximum score after performing m operations.

    Example 1:

    Input: nums = [1,2,3], multipliers = [3,2,1]
    Output: 14
    Explanation: An optimal solution is as follows:
    - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
    - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
    - Choose from the end, [1], adding 1 * 1 = 1 to the score.
    The total score is 9 + 4 + 1 = 14.
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(-5, -3, -3, -2, 7, 1)
        val multipliers = intArrayOf(-10, -5, 3, 4, 6)
        println(maximumScore(nums, multipliers))
        println(maximumScoreBottomUp(nums, multipliers))
    }

    private var n = 0
    private var m = 0
    private lateinit var nums: IntArray
    private lateinit var multipliers: IntArray
    private lateinit var memo: Array<IntArray>
    fun maximumScore(nums: IntArray, multipliers: IntArray): Int {
        n = nums.size
        m = multipliers.size
        MaximumScoreFromPerformingMultiplicationOperations.nums = nums
        MaximumScoreFromPerformingMultiplicationOperations.multipliers = multipliers
        memo = Array(m) { IntArray(m) }
        return dp(0, 0)
    }

    //We have 3 state variables required, index (i) for multiplier, left for left index and
    //right for right index which is calculated based on both indexes: i and left
    private fun dp(i: Int, left: Int): Int { //i represent index in multipliers array
        // Base case
        // We need to perform m operations, i equals m, that means we have no operations left.
        // Therefore, we should return 0.
        if (i == m) return 0
        val mult = multipliers[i]
        val right = n - 1 - (i - left)
        if (memo[i][left] == 0) {
            //Recurrence relation
            memo[i][left] = Math.max(
                mult * nums[left] + dp(i + 1, left + 1),
                mult * nums[right] + dp(i + 1, left)
            )
        }
        return memo[i][left]
    }

    //-------------------------------- BOTTOM UP
    /*
    Time complexity: O(m²), where M is the length of multipliers
     */
    fun maximumScoreBottomUp(nums: IntArray, multipliers: IntArray): Int {
        val n = nums.size
        val m = multipliers.size
        //We also need to initialize dp with one extra row
        // so that we don't go out of bounds in the first iteration of the outer loop.
        val dp = Array(m + 1) { IntArray(m + 1) }
        //we need to iterate backwards starting from m (because the base case happens
        // when i equals m
        for (i in m - 1 downTo 0) {
            for (left in i downTo 0) {
                val mult = multipliers[i]
                val right = n - 1 - (i - left)
                dp[i][left] = Math.max(
                    mult * nums[left] + dp[i + 1][left + 1],
                    mult * nums[right] + dp[i + 1][left]
                )
            }
        }
        return dp[0][0]
    }
}