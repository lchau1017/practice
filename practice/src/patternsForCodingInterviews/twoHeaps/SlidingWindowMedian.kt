package patternsForCodingInterviews.twoHeaps

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744001784_63Unit
class SlidingWindowMedian {
    //Equivalent of for the comparator : (a, b) -> b - a
    //Higher numbers are the priority
    var maxHeap = PriorityQueue(Collections.reverseOrder<Int>())
    var minHeap = PriorityQueue { a: Int, b: Int -> a - b }

    /*
    Time: O(N * K) where N is the total number of elements in the input.
    K is the size of sliding window
    Space: O(K)
     */
    fun findSlidingWindowMedian(nums: IntArray, k: Int): DoubleArray {
        val result = DoubleArray(nums.size - k + 1)
        for (i in nums.indices) {
            if (maxHeap.size == 0 || maxHeap.peek() >= nums[i]) {
                maxHeap.add(nums[i])
            } else {
                minHeap.add(nums[i])
            }
            rebalanceHeaps()
            if (i - k + 1 >= 0) { //If we have at least 'k' elements in the sliding window
                //Add the median to the result array
                if (maxHeap.size == minHeap.size) {
                    //We have even number of elements, take the average of middle two elements
                    result[i - k + 1] = maxHeap.peek() / 2.0 + minHeap.peek() / 2.0
                } else { //Because max-heap will have one more element than the min-heap
                    result[i - k + 1] = maxHeap.peek().toDouble()
                }
                //Remove the element going out of the sliding window
                val elementToBeRemoved = nums[i - k + 1]
                if (elementToBeRemoved <= maxHeap.peek()) {
                    maxHeap.remove(elementToBeRemoved)
                } else {
                    minHeap.remove(elementToBeRemoved)
                }
                rebalanceHeaps()
            }
        }
        return result
    }

    private fun rebalanceHeaps() {
        // either both the heaps will have equal number of elements or max-heap will have
        // one more element than the min-heap
        if (maxHeap.size < minHeap.size) {
            maxHeap.add(minHeap.poll())
        } else if (maxHeap.size > minHeap.size + 1) {
            minHeap.add(maxHeap.poll())
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var slidingWindowMedian = SlidingWindowMedian()
            val result: DoubleArray
            /*
        result = slidingWindowMedian.findSlidingWindowMedian(
                new int[] { 1, 2, -1, 3, 5 }, 2);
        System.out.print("Sliding window medians are: ");
        for (double num : result)
            System.out.print(num + " ");
        System.out.println();
         */slidingWindowMedian = SlidingWindowMedian()
            result = slidingWindowMedian.findSlidingWindowMedian(intArrayOf(1, 2, -1, 3, 5), 3)
            print("Sliding window medians are: ")
            for (num in result) print("$num ")
        }
    }
}