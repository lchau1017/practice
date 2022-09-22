package patternsForCodingInterviews.k_wayMerge

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744430509_111Unit
object SmallestNumberRange {
    @JvmStatic
    fun main(args: Array<String>) {
        val l1 = arrayOf(1, 5, 8)
        val l2 = arrayOf(4, 12)
        val l3 = arrayOf(7, 8, 10)
        val lists: MutableList<Array<Int>?> = ArrayList()
        lists.add(l1)
        lists.add(l2)
        lists.add(l3)
        val result = findSmallestRange(lists)
        print("Smallest range is: [" + result[0] + ", " + result[1] + "]")
    }

    /*
    Time: O(N * log M)
    Where N is the total number of elements in all the M input arrays.
    Space: O(M), at any time, min-heap will be store one number from all the M input
    arrays.
     */
    fun findSmallestRange(lists: List<Array<Int>?>): IntArray {
        val minHeap = PriorityQueue { n1: Node, n2: Node ->
            (lists[n1.arrayIndex]!![n1.elementIndex]
                    - lists[n2.arrayIndex]!![n2.elementIndex])
        }
        var rangeStart = 0
        var rangeEnd = Int.MAX_VALUE
        //Used to keep track of the largest number we have inserted in the heap
        var currentMaxNumber = Int.MIN_VALUE
        // put the 1st element of each array in the min heap
        for (i in lists.indices) {
            if (lists[i] != null) {
                minHeap.add(Node(0, i))
                currentMaxNumber = Math.max(
                    currentMaxNumber,
                    lists[i]!![0]
                )
            }
        }
        // take the smallest (top) element form the min heap,
        // if it gives us smaller range,
        // update the ranges, if the array of the top
        // element has more elements, insert the
        // next element in the heap
        while (minHeap.size == lists.size) {
            val node = minHeap.poll()
            // If these two numbers give us a smaller range, we update our range
            // We calcul the range and get the smaller one by updating
            // rangeStart & rangeEnd
            if (rangeEnd - rangeStart > currentMaxNumber
                - lists[node.arrayIndex]!![node.elementIndex]
            ) {
                //Update our range
                rangeStart = lists[node.arrayIndex]!![node.elementIndex]
                rangeEnd = currentMaxNumber
            }
            node.elementIndex++
            //If the array of the top element has more elements
            //we will insert the next element to the heap
            if (lists[node.arrayIndex]!!.size > node.elementIndex) {
                minHeap.add(node) //Insert the next element in the heap
                currentMaxNumber = Math.max(
                    currentMaxNumber,
                    lists[node.arrayIndex]!![node.elementIndex]
                )
            }
        }
        return intArrayOf(rangeStart, rangeEnd)
    }

    internal class Node(var elementIndex: Int, var arrayIndex: Int)
}