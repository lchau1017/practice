package patternsForCodingInterviews.cyclicSort

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743725641_32Unit
object FindDuplicateNumber {
    @JvmStatic
    fun main(args: Array<String>) {
        println(findNumber(intArrayOf(1, 4, 4, 3, 2)))
        println(findNumber(intArrayOf(2, 1, 3, 3, 5, 4)))
        println(findNumber(intArrayOf(2, 4, 1, 4, 4)))
        println(findDuplicateWithoutModifyingArray(intArrayOf(1, 4, 4, 3, 2)))
        /*
        System.out.println(findDuplicateWithoutModifyingArray(new int[] { 2, 1, 3, 3, 5, 4 }));
        System.out.println(findDuplicateWithoutModifyingArray(new int[] { 2, 4, 1, 4, 4 }));

         */
    }

    //TODO: Need to be reviewed
    /*
    Approach 2: We do not modify input array

     */
    fun findDuplicateWithoutModifyingArray(arr: IntArray): Int {
        var slow = 0
        var fast = 0
        do {
            slow = arr[slow]
            fast = arr[arr[fast]]
        } while (slow != fast)
        //Find cycle length
        var current = arr[slow]
        var cycleLength = 0
        do {
            current = arr[current]
            cycleLength++
        } while (current != arr[slow])
        return 0
    }

    private fun findStart(arr: IntArray, cycleLength: Int): Int {
        return 0
    }

    /*
    Approach 1: We modify the input array
    Time: O(N)
    Space: O(1)
     */
    fun findNumber(nums: IntArray): Int {
        var i = 0
        while (i < nums.size) {
            if (nums[i] != i + 1) {
                if (nums[i] != nums[nums[i] - 1]) {
                    swap(nums, i, nums[i] - 1)
                } else {
                    return nums[i]
                }
            } else {
                i++
            }
        }
        return -1
    }

    private fun swap(arr: IntArray, i: Int, j: Int) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }
}