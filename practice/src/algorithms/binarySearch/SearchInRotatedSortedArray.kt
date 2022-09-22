package algorithms.binarySearch

//https://leetcode.com/explore/learn/card/binary-search/125/template-i/952/
object SearchInRotatedSortedArray {
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(4, 5, 6, 7, 0, 1, 2)
        val target = 0
        println(search(nums, 5))
    }

    /*
    //Template #1
    Time: O(log N)
    Space O(1)
     */
    fun search(nums: IntArray, target: Int): Int {
        var start = 0
        var end = nums.size - 1
        while (start <= end) {
            val mid = start + (end - start) / 2
            if (nums[mid] == target) {
                return mid
            } else if (nums[mid] >= nums[start]) {
                //If the target is between the range
                if (target >= nums[start] && target < nums[mid]) end = mid - 1 else start = mid + 1
            } else {
                //If the target is located in the non-rotated subarray, go to the right
                if (target <= nums[end] && target > nums[mid]) start = mid + 1 else end = mid - 1
            }
        }
        return -1
    }
}