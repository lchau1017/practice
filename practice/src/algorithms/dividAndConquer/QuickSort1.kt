package algorithms.dividAndConquer

object QuickSort {
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(1, 5, 3, 2, 8, 7, 6, 4)
        quickSort(nums)
        for (i in nums) {
            print("$i, ")
        }
    }

    /* Sorts an array in the ascending order in O(n log n) time */ /*
    Time: O(NlogN)
     */
    fun quickSort(lst: IntArray) {
        val n = lst.size
        qsort(lst, 0, n - 1)
    }

    private fun qsort(lst: IntArray, lo: Int, hi: Int) {
        if (lo < hi) {
            val p = partition(lst, lo, hi)
            qsort(lst, lo, p - 1)
            qsort(lst, p + 1, hi)
        }
    }

    private fun partition(lst: IntArray, lo: Int, hi: Int): Int {
        /* Picks the last element hi as a pivot and returns the index of pivot value in the sorted array */
        val pivot = lst[hi]
        var i = lo
        for (j in lo until hi) {
            if (lst[j] < pivot) {
                //swap
                val tmp = lst[i]
                lst[i] = lst[j]
                lst[j] = tmp
                i++
            }
        }
        val tmp = lst[i]
        lst[i] = lst[hi]
        lst[hi] = tmp
        return i
    }
}