package patternsForCodingInterviews.subsets

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744050595_68Unit
object SubsetsWithDuplicates {
    @JvmStatic
    fun main(args: Array<String>) {
        val result: List<List<Int>>
        result = findSubsets(intArrayOf(1, 3, 3))
        println("Here is the list of subsets: \n$result")

        /*
        result = findSubsets(new int[] { 1, 5, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);

         */
    }

    /*
    Time: O(N * 2N)
    Space: O(2N)
     */
    fun findSubsets(nums: IntArray): List<List<Int>> {
        //Sort the numbers to handle duplicates
        //This ensure that all duplicates number are next to each other.
        Arrays.sort(nums)
        val subsets: MutableList<List<Int>> = ArrayList()
        subsets.add(ArrayList())
        var startIndex: Int
        var endIndex = 0
        for (i in nums.indices) {
            startIndex = 0
            //If the current and the previous elements are the same, create a new subsets only
            //from the subsets added in the previous step
            if (i > 0 && nums[i] == nums[i - 1]) {
                startIndex = endIndex + 1 //Get the index's subset with the same num
            }
            endIndex = subsets.size - 1
            //Pointer 'j' based on the subsets array
            for (j in startIndex..endIndex) {
                //Create a new subset from the existing subsets and add the current element to it
                val set: MutableList<Int> = ArrayList(subsets[j])
                set.add(nums[i])
                subsets.add(set)
            }
        }
        return subsets
    }
}