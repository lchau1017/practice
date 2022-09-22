package patternsForCodingInterviews.twoPointers

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743469029_6Unit
object TripletsWithSmallerSum {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            searchTriplets(intArrayOf(-1, 0, 2, 3), 3)
        )
        /*
        System.out.println(
                searchTriplets(new int[] { -1, 4, 2, 1, 3 }, 5));

         */
    }

    fun searchTriplets(arr: IntArray, target: Int): Int {
        Arrays.sort(arr)
        var count = 0
        for (i in 0 until arr.size - 2) {
            count += searchPair(arr, target - arr[i], i)
        }
        return count
    }

    private fun searchPair(arr: IntArray, targetSum: Int, first: Int): Int {
        var count = 0
        var left = first + 1
        var right = arr.size - 1
        while (left < right) {
            if (arr[left] + arr[right] < targetSum) { //Found triplet
                // since arr[right] >= arr[left], therefore, we can replace arr[right] by any
                // number between left and right to get a sum less than the target sum
                count += right - left
                left++
            } else {
                right-- //We need to pair with smaller number
            }
        }
        return count
    }
}