package algorithms.dynamicProgramming.commonPatternsContinued

//https://leetcode.com/explore/learn/card/dynamic-programming/633/common-patterns-continued/4144/
object StateReduction {
    @JvmStatic
    fun main(args: Array<String>) {
        println(fib(4))
    }

    //Fibanocci bottom Up approach
    /*
    Space complexity: O(1) instead of O(n) using an array
    Time complexity: O(n)
     */
    fun fib(n: Int): Int {
        if (n <= 1) return n
        var one_back = 1
        var two_back = 0
        for (i in 2..n) {
            val temp = one_back
            one_back += two_back
            two_back = temp
        }
        return one_back
    }
}