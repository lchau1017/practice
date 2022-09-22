package patternsForCodingInterviews.modifiedBinarySearch

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744165331_81Unit
object SearchInASortedInfiniteArray {
    @JvmStatic
    fun main(args: Array<String>) {
        var reader = ArrayReader(intArrayOf(4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30))
        println(search(reader, 16))
        println(search(reader, 11))
        reader = ArrayReader(intArrayOf(1, 3, 8, 10, 15))
        println(search(reader, 15))
        println(search(reader, 200))
    }

    /*
    Time: O(logN) for the first step increasing the bounds and binarySearch O(LogN) O(logN + logN) which is O(logN)
    Space: O(1)
     */
    fun search(reader: ArrayReader, key: Int): Int {
        //Find the proper bounds first
        var start = 0
        var end = 1
        while (reader[end] < key) { //ArrayReader.get(index) will return the number at index (sorted array)
            val newStart = end + 1
            end += (end - start) * 2 //Increase to double the bounds size
            start = newStart
        }
        return binarySearch(reader, key, start, end)
    }

    private fun binarySearch(reader: ArrayReader, key: Int, start: Int, end: Int): Int {
        var start = start
        var end = end
        while (start <= end) {
            val mid = start + (end - start) / 2
            if (key < reader[mid]) {
                end = mid - 1
            } else if (key > reader[mid]) {
                start = mid + 1
            } else { //Found the key
                return mid
            }
        }
        return -1
    }

    class ArrayReader(var arr: IntArray) {
        operator fun get(index: Int): Int {
            return if (index >= arr.size) {
                Int.MAX_VALUE
            } else {
                arr[index]
            }
        }
    }
}