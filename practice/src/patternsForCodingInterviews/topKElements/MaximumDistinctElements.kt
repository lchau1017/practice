package patternsForCodingInterviews.topKElements

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744333663_101Unit
object MaximumDistinctElements {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = findMaximumDistinctElements(intArrayOf(7, 3, 5, 8, 5, 3, 3), 2)
        println("Maximum distinct numbers after removing K numbers: $result")
        result = findMaximumDistinctElements(intArrayOf(3, 5, 12, 11, 12), 3)
        println("Maximum distinct numbers after removing K numbers: $result")
        result = findMaximumDistinctElements(intArrayOf(1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5), 2)
        println("Maximum distinct numbers after removing K numbers: $result")
    }

    /*
    Time: O(N * log N)
    Space: O(N)
     */
    fun findMaximumDistinctElements(nums: IntArray, k: Int): Int {
        var k = k
        var distinctElementCount = 0
        if (nums.size <= k) {
            return distinctElementCount
        }
        //find frequency each number
        val numFrequencyMap: MutableMap<Int, Int> = HashMap()
        for (i in nums) {
            numFrequencyMap[i] = numFrequencyMap.getOrDefault(i, 0) + 1
        }
        val minHeap =
            PriorityQueue { (_, value): Map.Entry<Int, Int>, (_, value1): Map.Entry<Int, Int> -> value - value1 }
        // insert all numbers with frequency greater than '1' into the min-heap
        for (entry in numFrequencyMap.entries) {
            if (entry.value == 1) {
                distinctElementCount++
            } else {
                minHeap.add(entry)
            }
        }
        // following a greedy approach, try removing the least frequent numbers first from
        // the min-heap
        while (k > 0 && !minHeap.isEmpty()) {
            val (_, value) = minHeap.poll()
            // to make an element distinct, we need to remove all of its occurrences except one
            k -= value - 1
            if (k >= 0) {
                distinctElementCount++
            }
        }
        // if k > 0, this means we have to remove some distinct numbers
        if (k > 0) {
            distinctElementCount -= k
        }
        return distinctElementCount
    }
}