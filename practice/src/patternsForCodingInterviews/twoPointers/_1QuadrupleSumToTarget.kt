package patternsForCodingInterviews.twoPointers

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743502365_9Unit
object _1QuadrupleSumToTarget {
    @JvmStatic
    fun main(args: Array<String>) {
        println(searchQuadruplets(intArrayOf(4, 1, 2, -1, 1, -3), 1))
        //System.out.println(searchQuadruplets(new int[] { 2, 0, -1, 1, -2, 2 }, 2));
    }

    /*
    Time: O(N * logN + N3) --> O(N3)
    Space: O(N)
     */
    fun searchQuadruplets(arr: IntArray, target: Int): List<List<Int>> {
        Arrays.sort(arr)
        val quadruplets: MutableList<List<Int>> = ArrayList()
        for (i in 0 until arr.size - 3) {
            //Avoid duplicate quadruplets
            if (i > 0 && arr[i - 1] == arr[i]) {
                continue
            }
            for (j in i + 1 until arr.size - 2) {
                if (j > i + 1 && arr[j] == arr[j - 1]) {
                    continue
                }
                searchPairs(arr, target, i, j, quadruplets)
            }
            println()
        }
        return quadruplets
    }

    private fun searchPairs(
        arr: IntArray,
        targetSum: Int,
        first: Int,
        second: Int,
        quadruplets: MutableList<List<Int>>
    ) {
        var left = second + 1
        var right = arr.size - 1
        while (left < right) {
            val sum = arr[first] + arr[second] + arr[left] + arr[right]
            if (targetSum == sum) {
                quadruplets.add(Arrays.asList(arr[first], arr[second], arr[left], arr[right]))
                left++
                right--
                while (left < right && arr[left] == arr[left - 1]) {
                    left++
                }
                while (left < right && arr[right] == arr[right + 1]) {
                    right--
                }
            } else if (sum < targetSum) {
                left++
            } else {
                right--
            }
        }
    }
}