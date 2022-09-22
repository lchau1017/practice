package patternsForCodingInterviews.modifiedBinarySearch

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744196413_85Unit
object _2SearchInRotatedArray {
    @JvmStatic
    fun main(args: Array<String>) {
        println(search(intArrayOf(10, 15, 1, 3, 8), 15))
        println(search(intArrayOf(4, 5, 7, 9, 10, -1, 2), 10))
        println(searchDuplicate(intArrayOf(3, 7, 3, 3, 3), 7))
        println("-> " + search(intArrayOf(2, 1, 0, 4, 5, 6, 7), 0))
    }

    /*
    IF Array has duplicate
    Time: O(LogN)
    Space: O(1)
     */
    fun searchDuplicate(arr: IntArray, key: Int): Int {
        var start = 0
        var end = arr.size - 1
        while (start <= end) {
            val mid = start + (end - start) / 2
            if (arr[mid] == key) return mid

            // the only difference from the previous solution,
            // if numbers at indexes start, mid, and end are same, we can't choose a side
            // the best we can do, is to skip one number from both ends as key != arr[mid]
            if (arr[start] == arr[mid] && arr[end] == arr[mid]) {
                ++start
                --end
                //Decide which part of the array is sorted
            } else if (arr[start] <= arr[mid]) { // left side is sorted in ascending order
                if (key >= arr[start] && key < arr[mid]) {
                    end = mid - 1
                } else { //key > arr[mid]
                    start = mid + 1
                }
            } else { // right side is sorted in ascending order
                if (key > arr[mid] && key <= arr[end]) {
                    start = mid + 1
                } else {
                    end = mid - 1
                }
            }
        }

        // we are not able to find the element in the given array
        return -1
    }

    /*
    IF Array Does not have duplicate
    Time: O(LogN)
    Space: O(1)
     */
    fun search(arr: IntArray, key: Int): Int {
        var start = 0
        var end = arr.size - 1
        while (start <= end) {
            val mid = start + (end - start) / 2
            if (arr[mid] == key) return mid
            if (arr[start] <= arr[mid]) { // left side is sorted in ascending order
                if (key >= arr[start] && key < arr[mid]) { //Between that ascending order range
                    end = mid - 1
                } else {
                    start = mid + 1
                }
            } else { // right side is sorted in ascending order
                if (key > arr[mid] && key <= arr[end]) {
                    start = mid + 1
                } else {
                    end = mid - 1
                }
            }
        }
        // we are not able to find the element in the given array
        return -1
    }
}