package patternsForCodingInterviews.subsets

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744042826_67Unit
object Subsets {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = findSubsets(intArrayOf(1, 3))
        println("Here is the list of subsets: $result")
        result = findSubsets(intArrayOf(1, 5, 3))
        println("Here is the list of subsets: $result")
    }

    /*
    Time: O(2n), each step, the number of subsets double where N total number of elements
    in the input O(N * 2N)
    Space: O(N)
     */
    fun findSubsets(nums: IntArray): List<List<Int?>?> {
        val subsets: MutableList<List<Int?>?> = ArrayList()
        //Start by adding the empty subset
        subsets.add(ArrayList())
        for (currentNumber in nums) {
            //We will take all existing subsets and insert the current number in them to create
            //new subsets
            val n = subsets.size
            for (i in 0 until n) {
                //Create a new subset from the existing subset and insert the current
                // element to it
                val set: MutableList<Int?> = ArrayList(subsets[i])
                set.add(currentNumber)
                subsets.add(set)
            }
        }
        return subsets
    }
}