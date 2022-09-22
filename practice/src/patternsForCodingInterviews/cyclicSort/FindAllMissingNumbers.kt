package patternsForCodingInterviews.cyclicSort

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743711992_31Unit
object FindAllMissingNumbers {
    @JvmStatic
    fun main(args: Array<String>) {
        val missing: List<Int>
        /*
        missing = findNumbers(
                new int[] { 2, 3, 1, 8, 2, 3, 5, 1 });
        System.out.println("Missing numbers: " + missing);

         */missing = findNumbers(intArrayOf(2, 4, 1, 2))
        println("Missing numbers: $missing")
        /*
        missing = findNumbers(new int[] { 2, 3, 2, 1 });
        System.out.println("Missing numbers: " + missing);

 */
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    fun findNumbers(nums: IntArray): List<Int> {
        var i = 0
        while (i < nums.size) {
            if (nums[i] != nums[nums[i] - 1]) { //all numbers from 1 to 8
                swap(nums, i, nums[nums[i] - 1])
            } else {
                i++
            }
        }
        val missingNumbers: MutableList<Int> = ArrayList()
        i = 0
        while (i < nums.size) {
            if (nums[i] != i + 1) {
                missingNumbers.add(i + 1)
            }
            i++
        }
        return missingNumbers
    }

    private fun swap(arr: IntArray, i: Int, j: Int) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }
}