package patternsForCodingInterviews.topKElements

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744351402_102Unit
object SumOfElements {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = findSumOfElements(intArrayOf(1, 3, 12, 5, 15, 11), 3, 6)
        println(
            "Sum of all numbers between k1 and k2 smallest numbers: $result"
        )
        result = findSumOfElements(intArrayOf(3, 5, 8, 7), 1, 4)
        println(
            "Sum of all numbers between k1 and k2 smallest numbers: $result"
        )
    }

    /*
    Time: O(N logK2) since we only the top K2 numbers in the max-heap at any tie
    Space: O(K2) need to store the smallest K2 numbers in the heap
     */
    fun findSumOfElements(nums: IntArray, k1: Int, k2: Int): Int {
        val minHeap = PriorityQueue { n1: Int, n2: Int -> n1 - n2 }
        //Insert all numbers to the min heap
        for (i in nums.indices) {
            minHeap.add(nums[i])
        }
        // remove k1 small numbers from the min heap
        for (i in 0 until k1) {
            minHeap.poll()
        }
        var elementSum = 0
        // sum next k2-k1-1 numbers
        for (i in 0 until k2 - k1 - 1) {
            elementSum += minHeap.poll()
        }
        return elementSum
    }
}