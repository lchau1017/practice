package algorithms.dynamicProgramming.strategicApproachToDP

//https://leetcode.com/explore/learn/card/dynamic-programming/631/strategy-for-solving-dp-problems/4097/
object HouseRobber {
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(1, 2, 1, 4, 9)
        println(rob(nums))
        println(robBottomUp(nums))
    }

    private val memo: MutableMap<Int, Int> = HashMap()
    private lateinit var nums: IntArray
    fun rob(nums: IntArray): Int {
        HouseRobber.nums = nums
        return dp(nums.size - 1)
    }

    private fun dp(i: Int): Int {
        //Base cases
        if (i == 0) return nums[i]
        if (i == 1) return Math.max(nums[0], nums[1])
        //Recurrence
        memo[i] = Math.max(
            dp(i - 1),
            dp(i - 2) + nums[i]
        )
        return memo[i]!!
    }

    //------------- BOTTOM UP
    fun robBottomUp(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]
        val dp = IntArray(nums.size)
        //Base cases
        dp[0] = nums[0] //Rob the first house
        dp[1] = Math.max(nums[0], nums[1]) //Rob the first or second house
        for (i in 2 until nums.size) {
            //Recurrence relation
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i])
        }
        return dp[nums.size - 1]
    }
}