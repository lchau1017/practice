package algorithms.binarySearch.templates

//https://leetcode.com/explore/learn/card/binary-search/135/template-iii/936/
object Template3 {
    /*
    Template #3 is another unique form of Binary Search. It is used to search for an element
    or condition which requires
    accessing the current index and its immediate left and right neighbor's index in the array.
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(1, 2, 3, 4)
        val target = 2
        println(binarySearch(nums, target))
    }

    fun binarySearch(nums: IntArray?, target: Int): Int {
        if (nums == null || nums.size == 0) return -1
        var left = 0
        var right = nums.size - 1
        while (left + 1 < right) {
            //Prevent (left + right) overflow
            val mid = left + (right - left) / 2
            if (nums[mid] == target) return mid else if (nums[mid] < target) left = mid else right = mid
        }
        //Post-processing
        // End Condition: left + 1 == right
        if (nums[left] == target) return left
        return if (nums[right] == target) right else -1
    }
}