package algorithms.binarySearch

//https://leetcode.com/explore/learn/card/binary-search/138/background/971/
object BinarySearch {
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(-1, 0, 3, 5, 9, 12)
        val target = 9
        //Output: 5 (index where target is located)
    }

    fun search(nums: IntArray, target: Int): Int {
        var pivot: Int
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            pivot = left + (right - left) / 2
            if (nums[pivot] == target) return pivot
            if (target < nums[pivot]) {
                //left side
                right = pivot - 1
            } else {
                left = pivot + 1
            }
        }
        return -1
    }
}