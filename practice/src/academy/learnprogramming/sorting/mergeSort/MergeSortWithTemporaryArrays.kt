package academy.learnprogramming.sorting.mergeSort

//https://www.baeldung.com/java-merge-sort
object MergeSortWithTemporaryArrays {
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(5, 1, 6, 2, 3, 4)
        mergeSort(nums, nums.size)
        for (i in nums) {
            print("$i, ")
        }
    }

    fun mergeSort(nums: IntArray, n: Int) {
        if (n < 2) //Only one element in the array
            return
        val mid = n / 2
        val l = IntArray(mid)
        val r = IntArray(n - mid)
        for (i in 0 until mid) {
            l[i] = nums[i]
        }
        for (i in mid until n) {
            r[i - mid] = nums[i]
        }
        mergeSort(l, mid) //Left side
        mergeSort(r, n - mid) //Right side
        merge(nums, l, r, mid, n - mid)
    }

    fun merge(a: IntArray, l: IntArray, r: IntArray, left: Int, right: Int) {
        var i = 0
        var j = 0
        var k = 0
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++]
            } else {
                a[k++] = r[j++]
            }
        }
        while (i < left) {
            a[k++] = l[i++]
        }
        while (j < right) {
            a[k++] = r[j++]
        }
    }
}