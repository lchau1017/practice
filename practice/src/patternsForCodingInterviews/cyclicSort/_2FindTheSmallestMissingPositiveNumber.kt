package patternsForCodingInterviews.cyclicSort

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743750267_35Unit
object _2FindTheSmallestMissingPositiveNumber {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            findNumber(intArrayOf(-3, 1, 5, 4, 2))
        )
        println(
            findNumber(intArrayOf(3, -2, 0, 1, 2))
        )
        println(
            findNumber(intArrayOf(3, 2, 5, 1))
        )
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    fun findNumber(nums: IntArray): Int {
        var i = 0
        while (i < nums.size) {
            if (nums[i] > 0 && nums[i] <= nums.size && nums[i] != nums[nums[i] - 1]) swap(nums, i, nums[i] - 1) else i++
        }
        i = 0
        while (i < nums.size) {
            if (nums[i] != i + 1) return i + 1
            i++
        }
        return nums.size + 1
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}