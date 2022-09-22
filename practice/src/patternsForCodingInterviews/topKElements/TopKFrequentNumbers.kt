package patternsForCodingInterviews.topKElements

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744303843_97Unit
object TopKFrequentNumbers {
    @JvmStatic
    fun main(args: Array<String>) {
        val nums3 = intArrayOf(1, 1, 1, 2, 2, 3)
        println(findTopKFrequentNumbers(nums3, 2))
        var result = findTopKFrequentNumbers(intArrayOf(1, 3, 5, 12, 11, 12, 11), 2)
        println("Here are the K frequent numbers: $result")
        result = findTopKFrequentNumbers(intArrayOf(5, 12, 11, 3, 11), 2)
        println("Here are the K frequent numbers: $result")
    }

    /*
    Time: O(N + N * log K)
    Sapce: O(N)
     */
    fun findTopKFrequentNumbers(nums: IntArray, k: Int): List<Int?> {
        // find the frequency of each number
        val numFrequencyMap: MutableMap<Int, Int> = HashMap()
        for (n in nums) {
            numFrequencyMap[n] = numFrequencyMap.getOrDefault(n, 0) + 1
        }
        val minHeap =
            PriorityQueue { (_, value): Map.Entry<Int, Int>, (_, value1): Map.Entry<Int, Int> -> value - value1 }
        // go through all numbers of the numFrequencyMap and push them in the minHeap, which
        // will have  top k frequent numbers.
        // If the heap size is more than k, we remove the smallest (top) number
        for (entry in numFrequencyMap.entries) {
            minHeap.add(entry)
            if (minHeap.size > k) {
                minHeap.poll()
            }
        }
        // create a list of top k numbers
        val topNumbers: MutableList<Int?> = ArrayList()
        while (!minHeap.isEmpty()) {
            topNumbers.add(minHeap.poll().key)
        }
        return topNumbers
    }
}