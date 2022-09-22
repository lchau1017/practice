package algorithms.backtracking.mustKnow

import java.util.*

//https://leetcode.com/problems/combination-sum/ (can reuse same element)
//https://leetcode.com/problems/combination-sum-ii/ (can not reuse same element
class CombinationSum {
    fun combinationSum(nums: IntArray, target: Int): List<List<Int>> {
        val output: MutableList<List<Int>> = ArrayList()
        Arrays.sort(nums)
        backtrack(output, ArrayList(), nums, target, 0)
        return output
    }

    private fun backtrack(
        output: MutableList<List<Int>>,
        currList: MutableList<Int>,
        nums: IntArray,
        remain: Int,
        start: Int
    ) {
        if (remain < 0) return else if (remain == 0) output.add(ArrayList(currList)) else {
            //Iterate all possbile candidates
            for (i in start until nums.size) {
                //Try this partial candidate solution
                currList.add(nums[i])
                //Move on
                backtrack(
                    output,
                    currList, nums, remain - nums[i], i
                ) // not i + 1 because we can reuse same elements
                //Backtrack
                currList.removeAt(currList.size - 1)
            }
        }
    }

    //Combination Sum II (can't reuse same element) : https://leetcode.com/problems/combination-sum-ii/
    /*
    Time: O(2n)
    Space: 0(N)
     */
    fun combinationSum2(nums: IntArray, target: Int): List<List<Int>> {
        val list: MutableList<List<Int>> = ArrayList()
        Arrays.sort(nums)
        backtrackSum2(list, ArrayList(), nums, target, 0)
        return list
    }

    private fun backtrackSum2(
        list: MutableList<List<Int>>, tempList: MutableList<Int>,
        nums: IntArray, remain: Int, start: Int
    ) {
        if (remain < 0) return else if (remain == 0) list.add(ArrayList(tempList)) else {
            for (i in start until nums.size) {
                if (i > start && nums[i] == nums[i - 1]) continue  // skip duplicates
                tempList.add(nums[i])
                backtrackSum2(list, tempList, nums, remain - nums[i], i + 1)
                tempList.removeAt(tempList.size - 1)
            }
        }
    }

    companion object {
        /*
    Example 1:
    Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

    Example 2:
    Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
     */
        @JvmStatic
        fun main(args: Array<String>) {
            val obj = CombinationSum()
            val candidates1 = intArrayOf(2, 3, 6, 7)
            val target1 = 7
            val candidates2 = intArrayOf(2, 3, 5)
            val target2 = 8
            var res: List<List<Int>>
            res = obj.combinationSum(candidates1, target1)
            for (l in res) {
                println(l)
            }
            res = obj.combinationSum2(candidates2, target1)
            println("Without replicate: ")
            for (l in res) {
                println(l)
            }
        }
    }
}