package patternsForCodingInterviews.twoPointers

import java.util.*

// https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743479436_7Unit
object SubarraysWithProductLessThanATarget {
    @JvmStatic
    fun main(args: Array<String>) {
        println(findSubarrays(intArrayOf(2, 5, 3, 10), 30))
        println(findSubarrays(intArrayOf(8, 2, 6, 5), 50))
    }

    /*
    Time: fo-loop: O(N)
    Creating subarrays take O(N²) Therefore: O(N3)
    Space: O(N) used for temp list
     */
    fun findSubarrays(arr: IntArray, target: Int): List<List<Int>> {
        val result: MutableList<List<Int>> = ArrayList()
        var product = 1.0
        var left = 0
        for (right in arr.indices) {
            product *= arr[right].toDouble()
            while (product >= target && left < arr.size) {
                product /= arr[left++].toDouble()
            }

            //LinkedList<Integer> tempList = new LinkedList<>();
            val tempList: MutableList<Int> = LinkedList()
            for (i in right downTo left) {
                //tempList.addFirst(nums[i])
                tempList.add(0, arr[i])
                result.add(ArrayList(tempList))
            }
        }
        return result
    }
}