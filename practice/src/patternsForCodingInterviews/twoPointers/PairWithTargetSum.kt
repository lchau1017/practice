package patternsForCodingInterviews.twoPointers

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743417172_1Unit
object PairWithTargetSum {
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(1, 2, 3, 4, 6)
        val target = 6
        //System.out.println(Arrays.toString(search(nums, target)));
        println(Arrays.toString(searchWithHashMap(nums, target)))
    }

    /*
    Time complexity: O(N)
    Space complexity: O(N), worst case, push N numbers in the HashTable.
     */
    fun searchWithHashMap(arr: IntArray, targetSum: Int): IntArray {
        //Let’s say during our iteration we are at number ‘X’,
        // so we need to find ‘Y’ such that “X + Y == TargetX+Y==Target”. We will do two things here:
        //Search for ‘Y’ (which is equivalent to “Target−X”) in the HashTable
        //Otherwise, insert “X” in the HashTable, so that we can search it for the later numbers.
        val nums: MutableMap<Int, Int> = HashMap() // to store numbers and indices.
        for (i in arr.indices) {
            if (nums.containsKey(targetSum - arr[i])) {
                return intArrayOf(nums[targetSum - arr[i]]!!, i)
            } else {
                nums[arr[i]] = i // put the number and its index in the map
            }
        }
        return intArrayOf(-1, -1)
    }

    /*
    Time complexity: O(N) where N total number of elements
    Space complexity: O(1)
     */
    fun search(arr: IntArray, targetSum: Int): IntArray {
        var left = 0
        var right = arr.size - 1
        while (left < right) {
            val currentSum = arr[left] + arr[right]
            if (currentSum == targetSum) {
                return intArrayOf(left, right)
            }
            if (targetSum > currentSum) {
                left++
            } else {
                right--
            }
        }
        return intArrayOf(-1, -1)
    }
}