package patternsForCodingInterviews.dynamicProgramming

//https://designgurus.org/path-player?courseid=grokking-dynamic-programming&unit=grokking-dynamic-programming_6126ffd8d78e2Unit
class Introduction {
    //Top-down without memoization
    fun calculateFibanocci(n: Int): Int {
        return if (n < 2) n else calculateFibanocci(n - 1) +
                calculateFibanocci(n - 2)
    }

    //Top-down with memoization
    fun calculateFibanocciTopDownMemoized(n: Int): Int {
        val memoize = IntArray(n + 1)
        return calculateFibanocciTopDownMemoized(memoize, n)
    }

    private fun calculateFibanocciTopDownMemoized(memoize: IntArray, n: Int): Int {
        if (n < 2) return n
        //If we have already solved this subproblem, return from cache
        if (memoize[n] != 0) {
            return memoize[n]
        }
        memoize[n] = (calculateFibanocciTopDownMemoized(memoize, n - 1)
                + calculateFibanocciTopDownMemoized(memoize, n - 2))
        return memoize[n]
    }

    //Bottom-Up with Tabulation
    fun calculateFibanocciBottomUp(n: Int): Int {
        if (n == 0) return 0
        val dp = IntArray(n + 1)
        //base cases
        dp[0] = 0
        dp[1] = 1
        for (i in 2..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val obj = Introduction()
            println(obj.calculateFibanocci(4))
            println(obj.calculateFibanocciTopDownMemoized(4))
            println(obj.calculateFibanocciBottomUp(4))
        }
    }
}