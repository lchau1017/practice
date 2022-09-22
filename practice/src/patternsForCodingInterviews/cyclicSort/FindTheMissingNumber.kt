package patternsForCodingInterviews.cyclicSort

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743705616_30Unit
object FindTheMissingNumber {
    @JvmStatic
    fun main(args: Array<String>) {
        println(findMissingNumber(intArrayOf(4, 0, 3, 1)))
        println(findMissingNumber(intArrayOf(8, 3, 5, 2, 4, 6, 0, 1)))
    }

    /*
    Time: O(N) + O(n -1) which is O(N)
    Space: O(1)
     */
    fun findMissingNumber(nums: IntArray): Int {
        var i = 0
        while (i < nums.size) {
            if (nums[i] < nums.size && nums[i] != nums[nums[i]]) {
                swap(nums, i, nums[i])
            } else {
                i++
            }
        }
        // find the first number missing from its index, that will be our required number
        i = 0
        while (i < nums.size) {
            if (nums[i] != i) {
                return i
            }
            i++
        }
        return nums.size
    }

    private fun swap(arr: IntArray, i: Int, j: Int) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }
}