package algorithms.binarySearch

import java.util.*

//https://leetcode.com/explore/learn/card/binary-search/144/more-practices/1035/
class TwoSum2_InputArraySorted {
    /*
    Time: O(N log N) but need to be confirmed !!!
    Space: O(1)
     */
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var left = 0
        var right = numbers.size - 1
        while (left + 1 < right) {
            val cur = numbers[left] + numbers[right]
            if (cur == target) {
                return intArrayOf(left + 1, right + 1)
            }
            if (cur < target) {
                // move end forward to the last value that numbers[end] <= target - numbers[start]
                left = smallestLargerOrEqual(numbers, target - numbers[right], left + 1, right)
            } else {
                // move start backward to the first value that numbers[start] >= target - numbers[end]
                right = largestSmallerOrEqual(numbers, target - numbers[left], left, right - 1)
            }
        }
        return intArrayOf(left + 1, right + 1)
    }

    private fun smallestLargerOrEqual(num: IntArray, target: Int, left: Int, right: Int): Int {
        var left = left
        var right = right
        while (left < right) {
            val mid = left + (right - left) / 2
            if (num[mid] == target) {
                return mid
            }
            if (num[mid] > target) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }

    private fun largestSmallerOrEqual(num: IntArray, target: Int, left: Int, right: Int): Int {
        var left = left
        var right = right
        while (left < right) {
            val mid = left + (right + 1 - left) / 2
            if (num[mid] == target) {
                return mid
            }
            if (num[mid] > target) {
                right = mid - 1
            } else {
                left = mid
            }
        }
        return left
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(2, 3, 4, 5)
            val target = 6
            val obj = TwoSum2_InputArraySorted()
            Arrays.stream(obj.twoSum(nums, target)).forEach { e: Int -> print("$e, ") }
        }
    }
}