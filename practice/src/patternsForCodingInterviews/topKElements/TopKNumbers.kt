package patternsForCodingInterviews.topKElements

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744276684_93Unit
object TopKNumbers {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = findKLargestNumbers(intArrayOf(3, 1, 5, 12, 2, 11), 3)
        println("Here are the top K numbers: $result")
        result = findKLargestNumbers(intArrayOf(5, 12, 11, -1, 12), 3)
        println("Here are the top K numbers: $result")
    }

    /*
    Time: O(N * logK)
     we can find the smallest number in a min-heap in constant time O(1)
     the smallest number is always at the root of the heap
     Extracting the smallest number from a min-heap will take O(logN)
     (if the heap has ‘N’ elements) as the heap needs to readjust after the removal of an element.
     Space: O(K) we need to store K numbers in the heap
     */
    fun findKLargestNumbers(nums: IntArray, k: Int): List<Int> {
        val minHeap = PriorityQueue { n1: Int, n2: Int -> n1 - n2 }
        //Put first 'K' numbers in the minHeap
        for (i in 0 until k) {
            minHeap.add(nums[i])
        }
        // go through the remaining numbers of the array, if the number from the array is
        // bigger than the top (smallest) number of the min-heap, remove the top number from
        // heap and add the number from array
        for (i in k until nums.size) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll()
                minHeap.add(nums[i])
            }
        }
        return ArrayList(minHeap)
    }
}