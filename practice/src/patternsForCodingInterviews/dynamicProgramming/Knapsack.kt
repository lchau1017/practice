package patternsForCodingInterviews.dynamicProgramming

//https://designgurus.org/path-player?courseid=grokking-dynamic-programming&unit=grokking-dynamic-programming_6126ffd8cf242Unit
class Knapsack {
    /** */ //Brut force
    /*
    Time: O(2n), n represents the total number of items.
    Space: O(n)
     */
    fun solveKnapsack(profits: IntArray, weights: IntArray, capacity: Int): Int {
        return knapsackRecursive(profits, weights, capacity, 0)
    }

    private fun knapsackRecursive(
        profits: IntArray, weights: IntArray, capacity: Int,
        currentIndex: Int
    ): Int {
        // base checks
        if (capacity <= 0 || currentIndex >= profits.size) return 0

        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't
        // process this
        var profit1 = 0
        //SELECT
        if (weights[currentIndex] <= capacity) profit1 = profits[currentIndex] + knapsackRecursive(
            profits,
            weights,
            capacity - weights[currentIndex],
            currentIndex + 1
        )

        // recursive call after excluding the element at the currentIndex
        //SKIP
        val profit2 = knapsackRecursive(
            profits,
            weights,
            capacity,
            currentIndex + 1
        )
        return Math.max(profit1, profit2)
    }

    /** */ //Top-Down With memoization
    /*
    O(N * C)
    Space: memoization 2D array: O(N * C), recursion: O(N)
    Which is O(N * C)
     */
    fun solveKnapsackTopDown(
        profits: IntArray,
        weights: IntArray,
        capacity: Int
    ): Int {
        //Since we have two changing values: capacity and currentIndex,
        //we can use two-dimensional array
        val dp = Array(profits.size) { Array(capacity + 1) { 0 } }

        return knapsackRecursiveTopDown(
            dp, profits, weights, capacity, 0
        )
    }

    private fun knapsackRecursiveTopDown(
        dp: Array<Array<Int>>,
        profits: IntArray,
        weights: IntArray,
        capacity: Int,
        currentIndex: Int
    ): Int {
        // base checks
        if (capacity <= 0 || currentIndex >= profits.size) return 0

        //We have already solved a similar problem
        if (dp[currentIndex][capacity] != null) {
            return dp[currentIndex][capacity]
        }
        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't
        // process this
        var profit1 = 0
        //SELECT
        if (weights[currentIndex] <= capacity) profit1 = profits[currentIndex] + knapsackRecursive(
            profits,
            weights,
            capacity - weights[currentIndex],
            currentIndex + 1
        )

        // recursive call after excluding the element at the currentIndex
        //SKIP
        val profit2 = knapsackRecursive(
            profits,
            weights,
            capacity,
            currentIndex + 1
        )
        dp[currentIndex][capacity] = Math.max(profit1, profit2)
        return dp[currentIndex][capacity]
    }

    /** */ //Bottom-Up
    /*
    Time: O(N * C)
    Space: O(N)
     */
    fun solveKnapsackBottomUp(
        profits: IntArray,
        weights: IntArray,
        capacity: Int
    ): Int {
        // basic checks
        if (capacity <= 0 || profits.size == 0 || weights.size != profits.size) return 0
        val n = profits.size
        val dp = Array(n) { IntArray(capacity + 1) }

        // populate the capacity=0 columns, with '0' capacity we have '0' profit
        for (i in 0 until n) dp[i][0] = 0

        // if we have only one weight, we will take it if it is not more than the capacity
        for (c in 0..capacity) {
            if (weights[0] <= c) dp[0][c] = profits[0]
        }

        // process all sub-arrays for all the capacities
        for (i in 1 until n) {
            for (c in 1..capacity) {
                var profit1 = 0
                var profit2 = 0
                // include the item, if it is not more than the capacity
                if (weights[i] <= c) profit1 = profits[i] + dp[i - 1][c - weights[i]]
                // exclude the item
                profit2 = dp[i - 1][c]
                // take maximum
                dp[i][c] = Math.max(profit1, profit2)
            }
        }

        // maximum profit will be at the bottom-right corner.
        return dp[n - 1][capacity]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var maxProfit = 0
            val ks = Knapsack()
            val profits = intArrayOf(1, 6, 10, 16)
            val weights = intArrayOf(1, 2, 3, 5)
            maxProfit = ks.solveKnapsack(profits, weights, 7)
            println("Recursive ---> $maxProfit")
            maxProfit = ks.solveKnapsack(profits, weights, 6)
            println("Recursive ---> $maxProfit")
            maxProfit = ks.solveKnapsackTopDown(profits, weights, 7)
            println("Top down ---> $maxProfit")
            maxProfit = ks.solveKnapsackTopDown(profits, weights, 6)
            println("Top Down ---> $maxProfit")
            maxProfit = ks.solveKnapsackBottomUp(profits, weights, 7)
            println("Bottom Up ---> $maxProfit")
            maxProfit = ks.solveKnapsackBottomUp(profits, weights, 6)
            println("Bottom Up ---> $maxProfit")
        }
    }
}