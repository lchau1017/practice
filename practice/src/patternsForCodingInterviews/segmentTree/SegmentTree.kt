package patternsForCodingInterviews.segmentTree

// Java Program to show segment tree operations like construction,
// query and update
internal class SegmentTree(arr: IntArray, n: Int) {
    var st: IntArray // The array that stores segment tree nodes

    /* Constructor to construct segment tree from given array. This
       constructor  allocates memory for segment tree and calls
       constructSTUtil() to  fill the allocated memory */
    init {
        // Allocate memory for segment tree
        //Height of segment tree
        val x = Math.ceil(Math.log(n.toDouble()) / Math.log(2.0)).toInt()

        //Maximum size of segment tree
        val max_size = 2 * Math.pow(2.0, x.toDouble()).toInt() - 1
        st = IntArray(max_size) // Memory allocation
        constructSTUtil(arr, 0, n - 1, 0)
    }

    // A utility function to get the middle index from corner indexes.
    fun getMid(s: Int, e: Int): Int {
        return s + (e - s) / 2
    }

    /*  A recursive function to get the sum of values in given range
        of the array.  The following are parameters for this function.
 
      st    --> Pointer to segment tree
      si    --> Index of current node in the segment tree. Initially
                0 is passed as root is always at index 0
      ss & se  --> Starting and ending indexes of the segment represented
                    by current node, i.e., st[si]
      qs & qe  --> Starting and ending indexes of query range */
    fun getSumUtil(ss: Int, se: Int, qs: Int, qe: Int, si: Int): Int {
        // If segment of this node is a part of given range, then return
        // the sum of the segment
        if (qs <= ss && qe >= se) return st[si]

        // If segment of this node is outside the given range
        if (se < qs || ss > qe) return 0

        // If a part of this segment overlaps with the given range
        val mid = getMid(ss, se)
        return getSumUtil(ss, mid, qs, qe, 2 * si + 1) +
                getSumUtil(mid + 1, se, qs, qe, 2 * si + 2)
    }

    /* A recursive function to update the nodes which have the given
       index in their range. The following are parameters
        st, si, ss and se are same as getSumUtil()
        i    --> index of the element to be updated. This index is in
                 input array.
       diff --> Value to be added to all nodes which have i in range */
    fun updateValueUtil(ss: Int, se: Int, i: Int, diff: Int, si: Int) {
        // Base Case: If the input index lies outside the range of
        // this segment
        if (i < ss || i > se) return

        // If the input index is in range of this node, then update the
        // value of the node and its children
        st[si] = st[si] + diff
        if (se != ss) {
            val mid = getMid(ss, se)
            updateValueUtil(ss, mid, i, diff, 2 * si + 1)
            updateValueUtil(mid + 1, se, i, diff, 2 * si + 2)
        }
    }

    // The function to update a value in input array and segment tree.
    // It uses updateValueUtil() to update the value in segment tree
    fun updateValue(arr: IntArray, n: Int, i: Int, new_val: Int) {
        // Check for erroneous input index
        if (i < 0 || i > n - 1) {
            println("Invalid Input")
            return
        }

        // Get the difference between new value and old value
        val diff = new_val - arr[i]

        // Update the value in array
        arr[i] = new_val

        // Update the values of nodes in segment tree
        updateValueUtil(0, n - 1, i, diff, 0)
    }

    // Return sum of elements in range from index qs (query start) to
    // qe (query end).  It mainly uses getSumUtil()
    fun getSum(n: Int, qs: Int, qe: Int): Int {
        // Check for erroneous input values
        if (qs < 0 || qe > n - 1 || qs > qe) {
            println("Invalid Input")
            return -1
        }
        return getSumUtil(0, n - 1, qs, qe, 0)
    }

    // A recursive function that constructs Segment Tree for array[ss..se].
    // si is index of current node in segment tree st
    fun constructSTUtil(arr: IntArray, ss: Int, se: Int, si: Int): Int {
        // If there is one element in array, store it in current node of
        // segment tree and return
        if (ss == se) {
            st[si] = arr[ss]
            return arr[ss]
        }

        // If there are more than one elements, then recur for left and
        // right subtrees and store the sum of values in this node
        val mid = getMid(ss, se)
        st[si] = constructSTUtil(arr, ss, mid, si * 2 + 1) +
                constructSTUtil(arr, mid + 1, se, si * 2 + 2)
        return st[si]
    }

    companion object {
        // Driver program to test above functions
        @JvmStatic
        fun main(args: Array<String>) {
            val arr = intArrayOf(1, 3, 5, 7, 9, 11)
            val n = arr.size
            val tree = SegmentTree(arr, n)

            // Build segment tree from given array

            // Print sum of values in array from index 1 to 3
            println(
                "Sum of values in given range = " +
                        tree.getSum(n, 1, 3)
            )

            // Update: set arr[1] = 10 and update corresponding segment
            // tree nodes
            tree.updateValue(arr, n, 1, 10)

            // Find sum after the value is updated
            println(
                "Updated sum of values in given range = " +
                        tree.getSum(n, 1, 3)
            )
        }
    }
}