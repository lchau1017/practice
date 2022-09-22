package patternsForCodingInterviews.cyclicSort

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743698633_29Unit
object CyclicSort {
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(3, 1, 5, 4, 2)
        sort(arr)
        for (num in arr) print("$num ")
        println()

        /*
        arr = new int[] { 2, 6, 4, 3, 1, 5 };
        CyclicSort.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 1, 5, 6, 4, 3, 2 };
        CyclicSort.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

         */
    }

    /*
    Time: O(n) + O(n-1) equivalent to O(N)
    Space: O(1)
     */
    fun sort(nums: IntArray) {
        var i = 0
        while (i < nums.size) {
            val j = nums[i] - 1
            if (nums[i] != nums[j]) {
                swap(nums, i, j)
            } else {
                i++
            }
        }
    }

    private fun swap(arr: IntArray, i: Int, j: Int) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }
}