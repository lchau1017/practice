package patternsForCodingInterviews.k_wayMerge

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744436924_112Unit
object _1KPairsWithLargestSums {
    @JvmStatic
    fun main(args: Array<String>) {
        val l1 = intArrayOf(9, 8, 2)
        val l2 = intArrayOf(6, 3, 1)
        val result = findKLargestPairs(l1, l2, 3)
        print("Pairs with largest sum are: ")
        for (pair in result) print("[" + pair[0] + ", " + pair[1] + "] ")
    }

    /*
    Time: O(N * M * logK) where N and M are the total number of elements in both arrays.
    Space: O(K) by storing K largest pairs.
     */
    fun findKLargestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<IntArray> {
        //smaller sum
        val minHeap = PriorityQueue { p1: IntArray, p2: IntArray -> p1[0] + p1[1] - (p2[0] + p2[1]) }
        //Keep in mind, the array is in decreasing order
        var i = 0
        while (i < nums1.size && i < k) {
            var j = 0
            while (j < nums2.size && j < k) {
                if (minHeap.size < k) {
                    minHeap.add(intArrayOf(nums1[i], nums2[j]))
                } else {
                    // if the sum of the two numbers from the two arrays is smaller than the
                    // smallest (top) element of the heap, we can 'break' here. Since the arrays
                    // are sorted in the descending order, we'll not be able to find a pair with a
                    // higher sum moving forward.
                    if (nums1[i] + nums2[j] < minHeap.peek()[0] + minHeap.peek()[1]) {
                        break
                    } else {
                        //else: we've a pair with a larger sum, remove top and insert this pair in heap
                        minHeap.poll()
                        minHeap.add(intArrayOf(nums1[i], nums2[j]))
                    }
                }
                j++
            }
            i++
        }
        return ArrayList(minHeap)
    }
}