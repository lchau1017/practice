package algorithms.backtracking.mustKnow

//https://leetcode.com/problems/permutations/  (without duplicate)
//https://leetcode.com/problems/permutations-ii/  (duplicate)
class Permutations {
    //Without duplicate
    //Permutations : https://leetcode.com/problems/permutations/
    fun permute(nums: IntArray): List<List<Int>> {
        val output: MutableList<List<Int>> = ArrayList()
        // Arrays.sort(nums); // not necessary
        backtrack(output, ArrayList(), nums)
        return output
    }

    private fun backtrack(output: MutableList<List<Int>>, currList: MutableList<Int>, nums: IntArray) {
        if (currList.size == nums.size) {
            output.add(ArrayList(currList))
        } else {
            for (i in nums.indices) {
                if (currList.contains(nums[i])) continue  // element already exists, skip
                currList.add(nums[i])
                backtrack(output, currList, nums)
                currList.removeAt(currList.size - 1)
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val obj = Permutations()
            val resultBacktracking = obj.permute(intArrayOf(1, 2, 3))
            for (l in resultBacktracking) {
                println(l)
            }
        }
    }
}