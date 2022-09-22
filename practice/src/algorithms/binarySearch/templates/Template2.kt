package algorithms.binarySearch.templates

//https://leetcode.com/explore/learn/card/binary-search/126/template-ii/937/
object Template2 {
    /*
    Template #2 is an advanced form of Binary Search. It is used to search for an element or condition
    which requires accessing
    the current index and its immediate right neighbor's index in the array
     */
    @JvmStatic
    fun main(args: Array<String>) {
    }

    fun binarySearch(nums: IntArray?, target: Int): Int {
        if (nums == null || nums.size == 0) return -1
        var left = 0
        var right = nums.size
        while (left < right) {
            //Prevent (left + right) overflow
            val mid = left + (right - left) / 2
            if (nums[mid] == target) return mid else if (nums[mid] < target) // Look at the right
                left = mid + 1 else right = mid
        }
        //Post-processing
        //End condition: left == right
        return if (left != nums.size && nums[left] == target) left else -1
    }
}