package dynamicProgrammingStudyPlan.dp1

//https://leetcode.com/problems/fibonacci-number/
object FibonacciNumber {
    @JvmStatic
    fun main(args: Array<String>) {
        println(fibTopDown(4))
        println(fibBottomUp(25))
    }

    //Correction:
    //https://leetcode.com/problems/fibonacci-number/discuss/215992/Java-Solutions
    //TOP-DOWN
    /*
    Time: O(n)
    Space: O(n)
     */
    var fib_cache = IntArray(31)
    fun fibTopDown(n: Int): Int {
        if (n <= 1) return n
        return if (fib_cache[n] != 0) {
            fib_cache[n]
        } else {
            (fibTopDown(n - 1)
                    + fibTopDown(n - 2)).also { fib_cache[n] = it }
        }
    }

    //BOTTOM UP
    /*
    Time: O(n)
    Space: O(n)
     */
    fun fibBottomUp(n: Int): Int {
        if (n <= 1) return n
        val dp = IntArray(n + 1)
        dp[0] = 0 //Line of code not necessary, array initialize by 0 by default
        dp[1] = 1
        for (i in 2 until dp.size) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }
}