package algorithms.dynamicProgramming.strategicApproachToDP

//https://leetcode.com/explore/learn/card/dynamic-programming/631/strategy-for-solving-dp-problems/4096/
object StrategyForSolvingDPProblems {
    @JvmStatic
    fun main(args: Array<String>) {
        val stairs = 4
        println(climbStairs(stairs))
        println(climbStairsMemo(stairs))
        println(climbStairsBottomUp(stairs))
    }

    /*
    Time complexity O(2n) -> without memoization because every call to dp
    creates 2 more calls to dp.
     */
    fun climbStairs(n: Int): Int {
        return dp(n)
    }

    // A function that represents the answer to the problem for a given state
    private fun dp(i: Int): Int {
        return if (i <= 2) i else dp(i - 1) + dp(i - 2)
        //Recurrence relation
    }

    /*
    Time complexity: O(n)
     */
    //------------------ Memoization Recursion
    private val memo: MutableMap<Int, Int> = HashMap()
    private fun dpMemo(i: Int): Int {
        if (i <= 2) return i
        // Instead of just returning dp(i - 1) + dp(i - 2), calculate it once and then
        // store it inside a hashmap to refer to in the future
        if (!memo.containsKey(i)) memo[i] =
            dp(i - 1) + dp(i - 2)
        return memo[i]!!
    }

    fun climbStairsMemo(n: Int): Int {
        return dp(n)
    }

    //--------------------------------BOTTOM-UP
    fun climbStairsBottomUp(n: Int): Int {
        if (n == 1) return 1

        // An array that represents the answer to the problem for a given state
        val dp = IntArray(n + 1)
        dp[1] = 1 // Base cases
        dp[2] = 2 // Base cases
        for (i in 3..n) {
            dp[i] = dp[i - 1] + dp[i - 2] // Recurrence relation
        }
        return dp[n]
    }
}