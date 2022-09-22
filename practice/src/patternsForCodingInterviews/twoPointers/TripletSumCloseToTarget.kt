package patternsForCodingInterviews.twoPointers

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743457252_5Unit
object TripletSumCloseToTarget {
    @JvmStatic
    fun main(args: Array<String>) {
        println(searchTriplet(intArrayOf(-2, 0, 1, 2), 2))
        println(searchTriplet(intArrayOf(-3, -1, 1, 2), 1))
        println(searchTriplet(intArrayOf(1, 0, 1, 1), 100))
    }

    /*
    Time: O(N log N), the function will take O(N * log N + N²) -> O(N²)
    Space:  O(N) caused by sorted array
     */
    fun searchTriplet(arr: IntArray?, targetSum: Int): Int {
        require(!(arr == null || arr.size < 3))
        Arrays.sort(arr)
        var smallestDifference = Int.MAX_VALUE
        for (i in 0 until arr.size - 2) {
            var left = i + 1
            var right = arr.size - 1
            while (left < right) {
                //Comparing the sum of three numbers to the targetSum
                val targetDiff = targetSum - arr[i] - arr[left] - arr[right]
                if (targetDiff == 0) { // found triplet with exact numbers
                    return targetSum //Return sum of all numbers
                }
                //Second part, find smallest difference
                if (Math.abs(targetDiff) < Math.abs(smallestDifference)
                    || (Math.abs(targetDiff) == Math.abs(smallestDifference)
                            && targetDiff > smallestDifference)
                ) {
                    //Save the closest and the biggest difference.
                    smallestDifference = targetDiff
                }
                if (targetDiff > 0) {
                    left++ //Need triplet with a bigger sum
                } else {
                    //Remove too much, we need smaller number (sorted array)
                    right-- //Need triplet with smaller sum
                }
            }
        }
        return targetSum - smallestDifference
    }
}