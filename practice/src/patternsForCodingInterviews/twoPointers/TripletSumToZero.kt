package patternsForCodingInterviews.twoPointers

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743445291_4Unit
object TripletSumToZero {
    @JvmStatic
    fun main(args: Array<String>) {
        println(searchTriplets(intArrayOf(-3, 0, 1, 2, -1, 1, -2)))
        //System.out.println(searchTriplets(new int[] { -5, 2, -1, -2, 3 }));
    }

    /*
    Time: Sorting the array will take: O(N * Log N)
    searchPair(): take O(N) and it is calling for every number in the input
    array.
    Total: O(N * log N + N²)  ---> O(N²)
    Space: O(N) caused by sorted array
     */
    fun searchTriplets(arr: IntArray): List<List<Int>> {
        Arrays.sort(arr)
        val triplets: MutableList<List<Int>> = ArrayList()
        for (i in 0 until arr.size - 2) {
            //Skip same element to avoid duplicate triplets
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue
            }
            searchPair(arr, -arr[i], i + 1, triplets)
        }
        return triplets
    }

    private fun searchPair(
        arr: IntArray, targetSum: Int,
        left: Int,
        triplets: MutableList<List<Int>>
    ) {
        var left = left
        var right = arr.size - 1
        while (left < right) {
            val currentSum = arr[left] + arr[right]
            if (currentSum == targetSum) {
                //Find a triplet
                triplets.add(Arrays.asList(-targetSum, arr[left], arr[right]))
                left++
                right--
                while (left < right && arr[left] == arr[left - 1]) {
                    //Skip same element to avoid duplicate triplets
                    left++
                }
                while (left < right && arr[right] == arr[right + 1]) {
                    //Skip same element to avoid duplciate
                    right--
                }
            } else if (targetSum > currentSum) {
                //Need to pair with a bigger sum
                left++
            } else {
                //Need to pair with smaller sum
                right--
            }
        }
    }
}