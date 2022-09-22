package algorithms.binarySearch

//https://leetcode.com/explore/learn/card/binary-search/136/template-analysis/1061/
class SearchInASortedArrayOfUnknownSize {
    /*
    Time: O(log T) where T is an index of target value caused by Binary Search
    Space: O(1)
     */
    fun search(reader: ArrayReader, target: Int): Int {
        if (reader[0] == target) return 0
        // search boundaries
        var left = 0
        var right = 1
        //While target is on the right to the right boundary: reader.get(right) < target:
        while (reader[right] < target) {
            //Set left boundary equal to the right one: left = right.
            left = right
            //Extend right boundary: right *= 2. To speed up, use right shift instead of multiplication: right <<= 1.
            //Left shift: x << 1. The same as multiplying by 2: x * 2.
            right = right shl 1
        }
        // binary search
        var pivot: Int
        var num: Int
        while (left <= right) {
            //Right shift: x >> 1. The same as dividing by 2: x / 2.
            pivot = left + (right - left shr 1)
            //Retrieve the element at this index: num = reader.get(pivot).
            num = reader[pivot]
            if (num == target) return pivot
            if (num > target) right = pivot - 1 else left = pivot + 1
        }
        // there is no target element
        return -1
    }

    interface ArrayReader {
        /*
        returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
        returns 231 - 1 if the i is out of the boundary of the array.
         */
        operator fun get(i: Int): Int
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
        }
    }
}