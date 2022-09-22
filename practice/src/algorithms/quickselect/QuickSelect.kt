package algorithms.quickselect

//https://www.geeksforgeeks.org/quickselect-algorithm/
object QuickSelect {
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(7, 10, 4, 3, 20, 15)
        val k = 3
        println(kthSmallest(arr, 0, arr.size - 1, k))
    }

    //Get the latest element has pivot
    //All smallest values are swapped to the left side
    private fun partition(
        arr: IntArray, low: Int,
        high: Int
    ): Int {
        val pivot = arr[high]
        var pivotloc = low
        for (i in low..high) {
            // inserting elements of less value
            // to the left of the pivot location
            if (arr[i] < pivot) {
                val temp = arr[i]
                arr[i] = arr[pivotloc]
                arr[pivotloc] = temp
                pivotloc++
            }
        }

        // swapping pivot to the final pivot location
        val temp = arr[high]
        arr[high] = arr[pivotloc]
        arr[pivotloc] = temp
        return pivotloc
    }

    // finds the kth position (of the sorted array)
    // in a given unsorted array i.e this function
    // can be used to find both kth largest and
    // kth smallest element in the array.
    // ASSUMPTION: all elements in arr[] are distinct
    fun kthSmallest(
        arr: IntArray, low: Int,
        high: Int, k: Int
    ): Int {
        // find the partition
        val partition = partition(arr, low, high)

        //recurs only for the part that contains the kth smallest element

        // if partition value is equal to the kth position,
        // return value at k.
        return if (partition == k - 1) arr[partition] else if (partition < k - 1) kthSmallest(
            arr,
            partition + 1,
            high,
            k
        ) else kthSmallest(
            arr,
            low,
            partition - 1,
            k
        )
    }
}