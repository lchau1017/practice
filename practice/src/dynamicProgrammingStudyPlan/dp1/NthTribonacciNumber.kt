package dynamicProgrammingStudyPlan.dp1

//https://leetcode.com/problems/n-th-tribonacci-number/?envType=study-plan&id=dynamic-programming-i
object NthTribonacciNumber {
    @JvmStatic
    fun main(args: Array<String>) {
        println(tribonacciBasicRecursive(25))
        println(tribonacciBottomUp(25))
        println(tribonacciTopDown(25))
    }

    //TOP-DOWN
    /*
    Time: O(N)
    Space: O(N)
     */
    var memo: MutableMap<Int, Int> = HashMap()
    fun tribonacciTopDown(n: Int): Int {
        if (memo.containsKey(n)) return memo[n]!!
        if (n == 0) return 0
        if (n <= 2) return 1
        memo[n] = (tribonacciBasicRecursive(n - 1)
                + tribonacciBasicRecursive(n - 2)
                + tribonacciBasicRecursive(n - 3))
        return memo[n]!!
    }

    //BOTTOM-UP
    /*
    Time: O(N)
    Space: O(N)
     */
    fun tribonacciBottomUp(n: Int): Int {
        val dp = IntArray(n + 1)
        dp[0] = 0 //Line of code not necessary
        dp[1] = 1
        dp[2] = 1
        for (i in 3 until dp.size) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
        }
        return dp[n]
    }

    //Basic recursive method
    /*
    Time: O(n)
    Space: O(n)
     */
    fun tribonacciBasicRecursive(n: Int): Int {
        if (n < 3) return if (n == 0) 0 else 1
        var tmp: Int
        var x = 0
        var y = 1
        var z = 1
        for (i in 3..n) {
            tmp = x + y + z
            x = y
            y = z
            z = tmp
        }
        return z
    }
}