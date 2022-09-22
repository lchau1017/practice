package patternsForCodingInterviews.subsets

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744061506_69Unit
object Permutations {
    @JvmStatic
    fun main(args: Array<String>) {
        val result = findPermutations(intArrayOf(1, 3, 5))
        print("Here are all the permutations: $result")
        val resultBacktracking = subsetsWithDup(intArrayOf(1, 2, 3))
        for (l in resultBacktracking) {
            println(l)
        }
    }

    //https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    //Approach 1: BackTracking Recursive (Subsets 2 containing duplicates)
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        val output: MutableList<List<Int>> = ArrayList()
        Arrays.sort(nums)
        backtrack(output, ArrayList(), nums, 0)
        return output
    }

    private fun backtrack(
        output: MutableList<List<Int>>,
        tempList: MutableList<Int>, nums: IntArray, start: Int
    ) {
        output.add(ArrayList(tempList))
        for (i in start until nums.size) {
            if (i > start && nums[i] == nums[i - 1]) continue  // skip duplicates
            tempList.add(nums[i])
            backtrack(output, tempList, nums, i + 1)
            tempList.removeAt(tempList.size - 1)
        }
    }

    /*
    Iterative
    O(N)
    Space: O(N * N)
     */
    fun findPermutations(nums: IntArray): List<List<Int>> {
        val result: MutableList<List<Int>> = ArrayList()
        val permutationsQueue: Queue<List<Int>> = LinkedList()
        permutationsQueue.add(ArrayList())
        for (currentNumber in nums) {
            // we will take all existing permutations and add the current number to create
            // new permutations
            val n = permutationsQueue.size
            for (i in 0 until n) {
                val oldPermutation = permutationsQueue.poll()
                // create a new permutation by adding the current number at every position
                for (j in 0..oldPermutation.size) {
                    val newPermutation: ArrayList<Int> = ArrayList(oldPermutation)
                    newPermutation.add(j, currentNumber)
                    if (newPermutation.size == nums.size) result.add(newPermutation) else permutationsQueue.add(
                        newPermutation
                    )
                }
            }
        }
        return result
    }

    /*
    Recursive
    Time: O(N∗N!)
    Space: O(N∗N!)
     */
    fun generatePermutationsRecursive(nums: IntArray): List<List<Int>> {
        val result: MutableList<List<Int>> = ArrayList()
        generatePermutationsRecursive(nums, 0, ArrayList(), result)
        return result
    }

    private fun generatePermutationsRecursive(
        nums: IntArray, index: Int,
        currentPermutation: List<Int>, result: MutableList<List<Int>>
    ) {
        if (index == nums.size) {
            result.add(currentPermutation)
        } else {
            // create a new permutation by adding the current number at every position
            for (i in 0..currentPermutation.size) {
                val newPermutation: ArrayList<Int> = ArrayList(currentPermutation)
                newPermutation.add(i, nums[index])
                generatePermutationsRecursive(nums, index + 1, newPermutation, result)
            }
        }
    }
}