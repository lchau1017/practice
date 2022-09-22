package algorithms.dynamicProgramming.commonPatternsContinued

//https://leetcode.com/explore/learn/card/dynamic-programming/633/common-patterns-continued/4136/
class CountingDP {
    //Number of distinct way
    //https://leetcode.com/explore/learn/card/dynamic-programming/633/common-patterns-continued/4137/
    internal class PaintFence {
        //Given the two integers n and k, return the number of ways you can paint the fence.
        private val memo: MutableMap<Int, Int> = HashMap()

        /*
        Time complexity: O(n)
        Space complexity: O(N) (Recursion stack)
         */
        fun numWays(n: Int, k: Int): Int {
            return totalWays(n, k) //One argument i, need one dimentional array
        }

        //k: number of different colors
        //i:
        // will determine the number of ways you can paint i fence posts.
        private fun totalWays(i: Int, k: Int): Int { //k ways to paint the fence posts
            if (i == 1) return k
            if (i == 2) // Allowed to paint two posts in a row with the same color
            // If we have two posts, then there are k * k ways to paint it
            // (since we are allowed to paint have two posts in a row be the same color).
            // Therefore, totalWays(1) = k, totalWays(2) = k * k.
                return k * k
            // Check if we have already calculated totalWays(i)
            if (memo.containsKey(i)) {
                return memo[i]!!
            }
            //Recurrence:
            memo[i] = (
                    (k - 1) //Use different color than previous post
                            * totalWays(i - 1, k) //
                            + (k - 1) //same color
                            //It is allowed to have 2 consecutive post with the same color
                            * totalWays(i - 2, k))
            return memo[i]!!
        }

        companion object {
            @JvmStatic
            fun main(args: Array<String>) {
                val n = 3
                val k = 2
                val obj = PaintFence()
                //Output 6
                println(obj.numWays(n, k))
            }
        }
    }

    //----------------------- BOTTOM UP
    /*
    Approach 3: Bottom-Up, Constant Space

    Time complexity: O(n)
    Space complexity;: O(1)
     */
    fun numWays(n: Int, k: Int): Int {
        if (n == 1) return k
        var twoPostsBack = k
        var onePostBack = k * k
        for (i in 3..n) {
            val curr = (k - 1) * (onePostBack + twoPostsBack)
            twoPostsBack = onePostBack
            onePostBack = curr
        }
        return onePostBack
    }
}