package patternsForCodingInterviews.dynamicProgramming

//https://designgurus.org/path-player?courseid=grokking-dynamic-programming&unit=grokking-dynamic-programming_6126ffd8cc61fUnit
class EqualSubsetSumPartition {
    //Brut force
    /*
    Time: O(2n) where N represents total number
    Space: O(n)
     */
    fun canPartition(nums: IntArray): Boolean {
        var sum = 0
        for (i in nums.indices) {
            sum += nums[i]
        }
        //If sum odd number, we can not have 2 subsets with equal sum
        return if (sum % 2 != 0) {
            false
        } else canPartitionRecursive(nums, sum / 2, 0)
    }

    private fun canPartitionRecursive(nums: IntArray, sum: Int, currentIndex: Int): Boolean {
        //base check
        if (sum == 0) return true
        if (nums.size == 0 || currentIndex >= nums.size) {
            return false
        }
        //Recursive call after choosing the number at the currentIndex
        //If the number at currentIndex exceeds the sum, we do not process this
        if (nums[currentIndex] <= sum) {
            if (canPartitionRecursive(
                    nums,
                    sum - nums[currentIndex],
                    currentIndex + 1
                )
            ) {
                return true
            }
        }
        //recursive call after excluding the number at the currentIndex
        return canPartitionRecursive(nums, sum, currentIndex + 1)
    }

    /** */ //Top-Down with Memoization
    /*
    need to store the results for every subset for every possible sum, we use
    2D array. First dimension: represent different subsets
    Second dimension: different sums that we calculate from each subset

    Time: O(N * S)
    N represents total numbers & S total sum of all numbers
    Space: O(N)
     */
    fun canPartitionTopDown(nums: IntArray): Boolean {
        var sum = 0
        for (i in nums.indices) {
            sum += nums[i]
        }
        if (sum % 2 != 0) {
            return false
        }
        val dp = Array(nums.size) { Array(sum / 2 + 1) { false } }
        return this.canPartitionRecursive(dp, nums, sum / 2, 0)
    }

    private fun canPartitionRecursive(
        dp: Array<Array<Boolean>>,
        num: IntArray,
        sum: Int,
        currentIndex: Int
    ): Boolean {
        //base check
        if (sum == 0) return true
        if (num.size == 0 || currentIndex >= num.size) return false
        // if we have not already processed a similar problem
        if (dp[currentIndex][sum] == null) {
            // recursive call after choosing the number at the currentIndex
            // if the number at currentIndex exceeds the sum, we shouldn't process this
            if (num[currentIndex] <= sum) {
                if (canPartitionRecursive(
                        dp,
                        num,
                        sum - num[currentIndex],
                        currentIndex + 1
                    )
                ) {
                    dp[currentIndex][sum] = true
                    return true
                }
            }
            // recursive call after excluding the number at the currentIndex
            dp[currentIndex][sum] = canPartitionRecursive(
                dp,
                num, sum, currentIndex + 1
            )
        }
        return dp[currentIndex][sum]
    }

    /** */ /*
    Bottom Up

    Time: O(N * S)N represents total numbers and S is the total sum of all numbers
    Space: 0(N)
     */
    fun canPartitionBottomUp(num: IntArray): Boolean {
        val n = num.size
        // find the total sum
        var sum = 0
        for (i in 0 until n) sum += num[i]

        // if 'sum' is a an odd number, we can't have two subsets with same total
        if (sum % 2 != 0) return false

        // we are trying to find a subset of given numbers that has a total sum of ‘sum/2’.
        sum /= 2
        val dp = Array(n) { BooleanArray(sum + 1) }

        // populate the sum=0 columns, as we can always for '0' sum with an empty set
        for (i in 0 until n) dp[i][0] = true

        // with only one number, we can form a subset only when the required sum is equal to
        // its value
        for (s in 1..sum) {
            dp[0][s] = if (num[0] == s) true else false
        }

        // process all subsets for all sums
        for (i in 1 until n) {
            for (s in 1..sum) {
                // if we can get the sum 's' without the number at index 'i'
                if (dp[i - 1][s]) {
                    dp[i][s] = dp[i - 1][s]
                } else if (s >= num[i]) {
                    // else we can find a subset to get the remaining sum
                    dp[i][s] = dp[i - 1][s - num[i]]
                }
            }
        }

        // the bottom-right corner will have our answer.
        return dp[n - 1][sum]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val ps = EqualSubsetSumPartition()
            var num: IntArray
            num = intArrayOf(1, 2, 3, 4)
            println(ps.canPartition(num))
            num = intArrayOf(1, 1, 3, 4, 7)
            println(ps.canPartition(num))
            num = intArrayOf(2, 3, 4, 6)
            println(ps.canPartition(num))
            println("\nTop Down : ")
            num = intArrayOf(1, 2, 3, 4)
            println(ps.canPartitionTopDown(num))
            num = intArrayOf(1, 1, 3, 4, 7)
            println(ps.canPartitionTopDown(num))
            num = intArrayOf(2, 3, 4, 6)
            println(ps.canPartitionTopDown(num))
            println("\nBottom Down : ")
            num = intArrayOf(1, 2, 3, 4)
            println(ps.canPartitionBottomUp(num))
            num = intArrayOf(1, 1, 3, 4, 7)
            println(ps.canPartitionBottomUp(num))
            num = intArrayOf(2, 3, 4, 6)
            println(ps.canPartitionBottomUp(num))
        }
    }
}