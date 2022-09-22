package algorithms.dynamicProgramming.DPForPathsInMatrix

import java.util.*

//https://leetcode.com/explore/learn/card/dynamic-programming/634/matrix-path-based-dp/4130/
class UniquePaths {
    //Recursive
    fun uniquePathsRecursive(m: Int, n: Int): Int {
        return if (m == 1 || n == 1) {
            1
        } else uniquePaths(m - 1, n) + uniquePaths(m, n - 1)
    }

    //Input: m = 3, n = 7
    //Bottom Up approach
    fun uniquePaths(m: Int, n: Int): Int {
        val d = Array(m) { IntArray(n) }
        for (arr in d) {
            Arrays.fill(arr, 1)
        }
        for (col in 1 until m) {
            for (row in 1 until n) {
                d[col][row] = d[col - 1][row] + d[col][row - 1]
            }
        }
        return d[m - 1][n - 1]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
        }
    }
}