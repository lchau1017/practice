package patternsForCodingInterviews.modifiedBinarySearch

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744141988_78Unit
object CeilingOfANumber {
    @JvmStatic
    fun main(args: Array<String>) {
        /*
        System.out.println(CeilingOfANumber.searchCeilingOfANumber(
                new int[] { 4, 6, 10 }, 6));

         */
        println(searchCeilingOfANumber(intArrayOf(1, 3, 8, 10, 15), 12))
        /*
        System.out.println(CeilingOfANumber.searchCeilingOfANumber(
                new int[] { 4, 6, 10 }, 17));
        System.out.println(CeilingOfANumber.searchCeilingOfANumber(
                new int[] { 4, 6, 10 }, -1));

         */
    }

    fun searchCeilingOfANumber(arr: IntArray, key: Int): Int {
        if (key > arr[arr.size - 1]) // if the 'key' is bigger than the biggest element
            return -1
        var start = 0
        var end = arr.size - 1
        while (start <= end) {
            val mid = start + (end - start) / 2
            if (key < arr[mid]) {
                end = mid - 1
            } else if (key > arr[mid]) {
                start = mid + 1
            } else { //Found the key
                return mid
            }
        }
        // since the loop is running until 'start <= end', so at the end of the while loop,
        // 'start == end+1' we are not able to find the element in the given array, so the
        // next big number will be arr[start]
        return start
    }
}