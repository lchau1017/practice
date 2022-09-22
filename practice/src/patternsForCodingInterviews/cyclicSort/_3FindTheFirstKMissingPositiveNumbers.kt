package patternsForCodingInterviews.cyclicSort

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743754602_36Unit
object _3FindTheFirstKMissingPositiveNumbers {
    @JvmStatic
    fun main(args: Array<String>) {
        val missingNumbers = findNumbers(intArrayOf(3, -1, 4, 5, 5), 3)
        println("Missing numbers: $missingNumbers")

        /*
        missingNumbers = findNumbers(new int[] { 2, 3, 4 }, 3);
        System.out.println("Missing numbers: " + missingNumbers);

        missingNumbers = findNumbers(new int[] { -2, -3, 4 }, 2);
        System.out.println("Missing numbers: " + missingNumbers);

         */
    }

    /*
    Time: O(n + k) as the last two loops will run for O(n) and O(k) times respectively
    Space: O(k) space to store extraNumbers
     */
    fun findNumbers(nums: IntArray, k: Int): List<Int> {
        var i = 0
        while (i < nums.size) {
            if (nums[i] > 0 && nums[i] <= nums.size && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1)
            } else {
                i++
            }
        }
        val missingNumbers: MutableList<Int> = ArrayList()
        val extraNumbers: MutableSet<Int> = HashSet()
        i = 0
        while (i < nums.size && missingNumbers.size < k) {
            if (nums[i] != i + 1) {
                missingNumbers.add(i + 1)
                extraNumbers.add(nums[i])
            }
            i++
        }
        // add the remaining missing numbers
        i = 1
        while (missingNumbers.size < k) {
            val candidateNumber = i + nums.size
            // ignore if the array contains the candidate number
            if (!extraNumbers.contains(candidateNumber)) {
                missingNumbers.add(candidateNumber)
            }
            i++
        }
        return missingNumbers
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}