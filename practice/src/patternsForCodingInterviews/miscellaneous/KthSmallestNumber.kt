package patternsForCodingInterviews.miscellaneous

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744623686_129Unit
object KthSmallestNumber {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = findKthSmallestNumber(intArrayOf(1, 5, 12, 2, 11, 5), 4)
        println("Kth smallest number is: $result")

        // since there are two 5s in the input array, our 3rd and 4th smallest numbers
        // should be a '5'
        result = findKthSmallestNumber(intArrayOf(1, 5, 12, 2, 11, 5), 4)
        println("Kth smallest number is: $result")
        result = findKthSmallestNumber(intArrayOf(5, 12, 11, -1, 12), 3)
        println("Kth smallest number is: $result")
    }

    /*
    Using QuickSort's Randomized partitioning Scheme
     */
    fun findKthSmallestNumberQuickSortRandomPivot(nums: IntArray, k: Int): Int {
        return findKthSmallestNumberRecRandom(nums, k, 0, nums.size - 1)
    }

    private fun findKthSmallestNumberRecRandom(
        nums: IntArray,
        k: Int,
        start: Int,
        end: Int
    ): Int {
        val p = partitionRandom(nums, start, end)
        if (p == k - 1) return nums[p]
        return if (p > k - 1) findKthSmallestNumberRecRandom(
            nums,
            k,
            start,
            p - 1
        ) else findKthSmallestNumberRecRandom(
            nums,
            k,
            p + 1,
            end
        )
    }

    private fun partitionRandom(nums: IntArray, low: Int, high: Int): Int {
        var low = low
        if (low == high) return low
        val randomNum = Random()
        val pivotIndex = low + randomNum.nextInt(high - low)
        swap(nums, pivotIndex, high)
        val pivot = nums[high]
        for (i in low until high) {
            if (nums[i] < pivot) {
                swap(nums, low++, i)
            }
        }
        //put the pivot in its correct place
        swap(nums, low, high)
        return low
    }

    /*
    Partition Scheme Of QuickSort
    Time: Worst case: O(N²)
    Space: O(N) for recursion stack
     */
    fun findKthSmallestNumberQuickSort(nums: IntArray, k: Int): Int {
        return findKthSmallestNumberRec(nums, k, 0, nums.size - 1)
    }

    private fun findKthSmallestNumberRec(nums: IntArray, k: Int, start: Int, end: Int): Int {
        val p = partition(nums, start, end)
        if (p == k - 1) return nums[p]
        return if (p > k - 1) findKthSmallestNumberRec(
            nums,
            k,
            start,
            p - 1
        ) else findKthSmallestNumberRec(
            nums,
            k,
            p + 1,
            end
        )
        // search higher part
    }

    private fun partition(nums: IntArray, low: Int, high: Int): Int {
        var low = low
        if (low == high) return low
        val pivot = nums[high]
        for (i in low until high) {
            // all elements less than 'pivot' will be before the index 'low'
            if (nums[i] < pivot) swap(nums, low++, i)
        }
        // put the pivot in its correct place
        swap(nums, low, high)
        return low
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }

    /*
    Using MinHeap
    Time: Building heap with N elements will take O(N)
    Extracting K numbers will take O(K * logN)
    Overall: O(N + K * logN)
    Space: O(N)
     */
    fun findKthSmallestNumberMinHeap(nums: IntArray, k: Int): Int {
        val maxHeap = PriorityQueue { n1: Int, n2: Int -> n2 - n1 }
        // put first k numbers in the max heap
        for (i in 0 until k) {
            maxHeap.add(nums[i])
        }
        // go through the remaining numbers of the array, if the number from the array is
        // smaller than the top (biggest) number of the heap, remove the top number from
        // heap and add the number from array
        for (i in k until nums.size) {
            if (nums[i] < maxHeap.peek()) {
                maxHeap.poll()
                maxHeap.add(nums[i])
            }
        }
        // the root of the heap has the Kth smallest number
        return maxHeap.peek()
    }

    /*
    Brut force using sorting
    Time: O(N logN) if we are not using in-place algorithm
    Space: O(N)
     */
    fun findKthSmallestNumberBrutForceSorting(nums: IntArray, k: Int): Int {
        Arrays.sort(nums)
        return nums[k - 1]
    }

    /*
    Brut force
     */
    //Time: O(N * K)
    //Space: O(1)
    fun findKthSmallestNumber(nums: IntArray, k: Int): Int {
        var previousSmallestNum = Int.MIN_VALUE
        var previousSmallestIndex = -1
        var currentSmallestNum = Int.MAX_VALUE
        var currentSmallestIndex = -1
        for (i in 0 until k) {
            for (j in nums.indices) {
                if (nums[j] > previousSmallestNum && nums[j] < currentSmallestNum) {
                    //Found the next smallest number
                    currentSmallestNum = nums[j]
                    currentSmallestIndex = j
                } else if (nums[j] == previousSmallestNum && j > previousSmallestIndex) {
                    // found a number which is equal to the previous smallest number; since numbers
                    // can repeat, we will consider 'nums[j]' only if it has a different index
                    // than previous smallest
                    currentSmallestNum = nums[j]
                    currentSmallestIndex = j
                    break // break here as we have found our definitive next smallest number
                }
            }
            // current smallest number becomes previous smallest number for the next iteration
            previousSmallestNum = currentSmallestNum
            previousSmallestIndex = currentSmallestIndex
            currentSmallestNum = Int.MAX_VALUE
        }
        return previousSmallestNum
    }
}