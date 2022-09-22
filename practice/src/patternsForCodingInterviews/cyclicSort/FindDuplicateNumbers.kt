package patternsForCodingInterviews.cyclicSort

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743734011_33Unit
object FindDuplicateNumbers {
    @JvmStatic
    fun main(args: Array<String>) {
        var duplicates = findNumbers(intArrayOf(3, 4, 4, 5, 5))
        println("Duplicates are: $duplicates")
        duplicates = findNumbers(intArrayOf(5, 4, 7, 2, 3, 5, 3))
        println("Duplicates are: $duplicates")
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    fun findNumbers(nums: IntArray): List<Int?> {
        var i = 0
        while (i < nums.size) {
            if (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1)
            } else {
                i++
            }
        }
        val duplicateNumbers: MutableList<Int?> = ArrayList()
        i = 0
        while (i < nums.size) {
            if (nums[i] != i + 1) {
                duplicateNumbers.add(nums[i])
            }
            i++
        }
        return duplicateNumbers
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}