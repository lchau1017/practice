package patternsForCodingInterviews.topKElements

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744325863_100Unit
object KClosestNumbers {
    @JvmStatic
    fun main(args: Array<String>) {
        //Kth closest number to X
        var result = findClosestElements(intArrayOf(5, 6, 7, 8, 9), 3, 7)
        println("'K' closest numbers to 'X' are: $result")
        println(findClosestElementsTwoPointers(intArrayOf(5, 6, 7, 8, 9), 3, 7))
        result = findClosestElements(intArrayOf(2, 4, 5, 6, 9), 3, 6)
        println("'K' closest numbers to 'X' are: $result")
        result = findClosestElements(intArrayOf(2, 4, 5, 6, 9), 3, 10)
        println("'K' closest numbers to 'X' are: $result")
    }

    fun findClosestElements(arr: IntArray, K: Int, X: Int): List<Int?> {
        //Since the array is sorted, we can first find the number closest to ‘X’
        // through Binary Search. Let’s say that number is ‘Y’.
        val index = binarySearch(arr, X)
        var low = index - K // Get k elements before index (it is a sorted array)
        var high = index + K //Get K elements after index (it is a sorted array)
        //Avoid to have negative low and high
        low = Math.max(low, 0)
        high = Math.min(high, arr.size - 1)
        val minHeap = PriorityQueue { n1: Entry, n2: Entry -> n1.key - n2.key }
        // add all candidate elements to the min heap, sorted by their absolute difference
        // from 'X'
        for (i in low..high) {
            minHeap.add(Entry(Math.abs(arr[i] - X), i))
        }
        // we need the top 'K' elements having smallest difference from 'X'
        val result: MutableList<Int> = ArrayList()
        for (i in 0 until K) {
            result.add(arr[minHeap.poll().value])
        }
        result.sort()
        return result
    }

    /*
    Other Approach Using Two Pointers
    */
    fun findClosestElementsTwoPointers(arr: IntArray, K: Int, X: Int): List<Int> {
        val result: MutableList<Int> = LinkedList()
        val index = binarySearch(arr, X)
        var leftPointer = index
        var rightPointer = index + 1
        for (i in 0 until K) {
            if (leftPointer >= 0 && rightPointer < arr.size) {
                val diff1 = Math.abs(X - arr[leftPointer])
                val diff2 = Math.abs(X - arr[rightPointer])
                if (diff1 <= diff2) result.add(0, arr[leftPointer--]) // append in the beginning
                else result.add(arr[rightPointer++]) // append at the end
            } else if (leftPointer >= 0) { //Case if right pointer can not go forward
                result.add(0, arr[leftPointer--])
            } else if (rightPointer < arr.size) { //Case if left pointer can not go backward
                result.add(arr[rightPointer++])
            }
        }
        return result
    }

    private fun binarySearch(arr: IntArray, target: Int): Int {
        var low = 0
        var high = arr.size - 1
        while (low <= high) {
            val mid = low + (high - low) / 2
            if (arr[mid] == target) {
                return mid
            }
            if (arr[mid] < target) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }
        return if (low > 0) {
            low - 1
        } else low
    }

    internal class Entry(var key: Int, var value: Int)
}