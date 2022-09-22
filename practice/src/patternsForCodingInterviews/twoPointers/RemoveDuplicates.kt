package patternsForCodingInterviews.twoPointers

object RemoveDuplicates {
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(2, 3, 3, 3, 6, 9, 9)
        println(remove(nums))
        val nums1 = intArrayOf(3, 2, 3, 6, 3, 10, 9, 3)
        val key = 3
        println(removeUnsortedArray(nums1, key))
    }

    /*
    Similar Questions:
    Problem 1: Given an unsorted array of numbers and a target ‘key’,
    remove all instances of ‘key’ in-place and return the new length of the array.
     */
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    fun removeUnsortedArray(arr: IntArray, key: Int): Int {
        var nextElement = 0 // index of the next element which is not 'key'
        for (i in arr.indices) {
            if (arr[i] != key) {
                arr[nextElement] = arr[i]
                nextElement++
            }
        }
        return nextElement
    }

    /*
    Approach 1:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    fun remove(arr: IntArray): Int {
        var nextNonDuplicate = 1 // Index of the next element non-duplicate
        for (i in arr.indices) {
            if (arr[nextNonDuplicate - 1] != arr[i]) {
                arr[nextNonDuplicate] = arr[i]
                nextNonDuplicate++
            }
        }
        return nextNonDuplicate
    }
}