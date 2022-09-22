package algorithms.dynamicProgramming.commonPatternsInDP

//https://leetcode.com/explore/learn/card/dynamic-programming/632/common-patterns-in-dp-problems/4116/
class BestTimeToBuyAndSellStockIV {
    private lateinit var prices: IntArray
    private lateinit var memo: Array<Array<IntArray>>
    fun maxProfit(k: Int, prices: IntArray): Int {
        this.prices = prices
        memo = Array(prices.size) { Array(k + 1) { IntArray(2) } }
        //start on day 0, with k transactions remaining and not holding a stock.
        //holding: 0 -> we are not holding a stock (option to buy a stock)
        // holding: 1 -> We are holding a stock (option ot sell a stock)
        return dp(0, k, 0)
    }

    //transactionsRemaining: represents how many transaction we have left.
    private fun dp(i: Int, transactionsRemaining: Int, holding: Int): Int {
        // Base cases
        if (transactionsRemaining == 0 || i == prices.size) {
            return 0
        }
        if (memo[i][transactionsRemaining][holding] == 0) {
            //move onto the next day with the same number of transactions,
            // while still holding the stock
            val doNothing = dp(i + 1, transactionsRemaining, holding)
            val doSomething: Int
            doSomething = if (holding == 1) { //Holding a stock
                // Sell Stock
                prices[i] + dp(
                    i + 1,
                    transactionsRemaining - 1,
                    0
                )
            } else { //Does not hold stock
                // Buy Stock
                -prices[i] + dp(
                    i + 1,
                    transactionsRemaining,
                    1
                )
            }
            // Recurrence relation. Choose the most profitable option.
            memo[i][transactionsRemaining][holding] = Math.max(doNothing, doSomething)
        }
        return memo[i][transactionsRemaining][holding]
    }

    //--------------------------- BOTTOM UP
    fun maxProfitBottomUp(k: Int, prices: IntArray): Int {
        val n = prices.size
        val dp = Array(n + 1) { Array(k + 1) { IntArray(2) } }
        for (i in n - 1 downTo 0) {
            for (transactionsRemaining in 1..k) {
                for (holding in 0..1) {
                    val doNothing = dp[i + 1][transactionsRemaining][holding]
                    var doSomething: Int
                    doSomething = if (holding == 1) {
                        // Sell stock
                        prices[i] + dp[i + 1][transactionsRemaining - 1][0]
                    } else {
                        // Buy stock
                        -prices[i] + dp[i + 1][transactionsRemaining][1]
                    }

                    // Recurrence relation
                    dp[i][transactionsRemaining][holding] = Math.max(doNothing, doSomething)
                }
            }
        }
        return dp[0][k][0]
    }

    companion object {
        //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(3, 2, 6, 5, 0, 3)
            val k = 2
            val obj = BestTimeToBuyAndSellStockIV()
            //Output 7
            println(obj.maxProfit(k, nums))
            println(obj.maxProfitBottomUp(k, nums))
        }
    }
}