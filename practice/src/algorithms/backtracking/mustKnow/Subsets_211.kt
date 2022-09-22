package algorithms.backtracking.mustKnow

import java.util.*

//https://leetcode.com/problems/subsets-ii/
class Subsets_2 {
    //Subsets II (contains duplicates) : https://leetcode.com/problems/subsets-ii/
    /*
    Time: O(N * 2n) to generate all subsets and then copy them into output list
    Space: O(N), to maintain the list and modifying list in place. We do not count space for output
     */
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        val output: MutableList<List<Int>> = ArrayList()
        Arrays.sort(nums)
        backtrack(output, ArrayList(), nums, 0)
        return output
    }

    private fun backtrack(list: MutableList<List<Int>>, currList: MutableList<Int>, nums: IntArray, start: Int) {
        list.add(ArrayList(currList))
        //Iterate all possible candidate
        for (i in start until nums.size) {
            if (i > start && nums[i] == nums[i - 1]) continue  // skip duplicates
            //Candidate solution
            currList.add(nums[i])
            //Move on to the next elements
            backtrack(list, currList, nums, i + 1)
            //backtrack removing the number before moving onto the next
            currList.removeAt(currList.size - 1)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val obj = Subsets_2()
            val resultBacktracking = obj.subsetsWithDup(intArrayOf(1, 2, 3))
            for (l in resultBacktracking) {
                println(l)
            }
        }
    }
}