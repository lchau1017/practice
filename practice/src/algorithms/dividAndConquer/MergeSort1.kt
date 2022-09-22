package algorithms.dividAndConquer

import java.util.*

//https://leetcode.com/explore/learn/card/recursion-ii/470/divide-and-conquer/2868/
object MergeSort {
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(1, 5, 3, 2, 8, 7)
        val result = merge_sort(nums)
        for (i in result) {
            print("$i, ")
        }
    }

    /*
    Time: O(n log n)
    Space: O(N)
     */
    fun merge_sort(input: IntArray): IntArray {
        if (input.size <= 1) return input
        val pivot = input.size / 2 //Divide
        //we recursively sort the sublists in the previous step (Conquer)
        val left_list = merge_sort(Arrays.copyOfRange(input, 0, pivot))
        val right_list = merge_sort(Arrays.copyOfRange(input, pivot, input.size))
        //Merge the sorted sublists in the above step (Combine)
        return merge(left_list, right_list)
    }

    private fun merge(left_list: IntArray, right_list: IntArray): IntArray {
        //Create a new array to place our elements in order to be sorted
        val ret = IntArray(left_list.size + right_list.size)
        var left_cursor = 0
        var right_cursor = 0
        var ret_cursor = 0
        while (left_cursor < left_list.size
            && right_cursor < right_list.size
        ) {
            //Smaller placed to the left side of this new array 'ret'
            if (left_list[left_cursor] < right_list[right_cursor]) {
                ret[ret_cursor++] = left_list[left_cursor++]
            } else {
                ret[ret_cursor++] = right_list[right_cursor++]
            }
        }
        //Append what is remain the above list
        while (left_cursor < left_list.size) {
            ret[ret_cursor++] = left_list[left_cursor++]
        }
        while (right_cursor < right_list.size) {
            ret[ret_cursor++] = right_list[right_cursor++]
        }
        return ret
    }
}