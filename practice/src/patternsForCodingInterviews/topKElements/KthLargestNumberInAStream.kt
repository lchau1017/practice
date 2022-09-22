package patternsForCodingInterviews.topKElements

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744318099_99Unit
class KthLargestNumberInAStream(nums: IntArray, val k: Int) {
    var minHeap = PriorityQueue { n1: Int, n2: Int -> n1 - n2 }

    init {
        // add the numbers in the min heap
        for (i in nums.indices) {
            add(nums[i])
        }
    }

    fun add(num: Int): Int {
        // add the new number in the min heap
        minHeap.add(num)
        // if heap has more than 'k' numbers, remove one number
        if (minHeap.size > k) {
            minHeap.poll()
        }
        // return the 'Kth largest number
        return minHeap.peek()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = intArrayOf(3, 1, 5, 12, 2, 11)
            val kthLargestNumber = KthLargestNumberInAStream(input, 4)
            println("4th largest number is: " + kthLargestNumber.add(6))
            println("4th largest number is: " + kthLargestNumber.add(13))
            println("4th largest number is: " + kthLargestNumber.add(4))
        }
    }
}