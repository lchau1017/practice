package patternsForCodingInterviews.cyclicSort

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743744011_34Unit
object _1FindTheCorruptPair {
    @JvmStatic
    fun main(args: Array<String>) {
        var nums = findNumbers(intArrayOf(3, 1, 2, 5, 2))
        println(nums[0].toString() + ", " + nums[1])
        nums = findNumbers(intArrayOf(3, 1, 2, 3, 6, 4))
        println(nums[0].toString() + ", " + nums[1])
    }

    /*
    Time: O(n)
    Space: O(1)
     */
    fun findNumbers(nums: IntArray): IntArray {
        var i = 0
        while (i < nums.size) {
            if (nums[i] != nums[i] - 1) {
                swap(nums, i, nums[i] - 1)
            }
        }
        i = 0
        while (i < nums.size) {
            if (nums[i] != i + 1) {
                return intArrayOf(nums[i], i + 1)
            }
            i++
        }
        return intArrayOf(-1, -1)
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}