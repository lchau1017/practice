package algorithms.binarySearch.templates

//https://leetcode.com/explore/learn/card/binary-search/125/template-i/938/
object Template1 {
    /*
    Template #1 is used to search for an element
    or condition which can be determined by accessing a single index in the array.
     */
    /*
    Most basic and elementary form of Binary Search
Search Condition can be determined without comparing to the element's neighbors
(or use specific elements around it)
No post-processing required because at each step, you are checking to see if the element has been found.
If you reach the end, then you know the element is not found
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(1, 2, 3, 4)
        val target = 5
        println(binarySearch(nums, target))
    }

    //Template #1:
    fun binarySearch(nums: IntArray?, target: Int): Int {
        if (nums == null || nums.size == 0) return -1
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            //Prevent (left + right) overflow
            val mid = left + (right - left) / 2
            if (nums[mid] == target) return mid else if (nums[mid] < target) // Look on right
                left = mid + 1 else  // Look on left
                right = mid - 1
        }
        //End condition left > right
        return -1
    }
}