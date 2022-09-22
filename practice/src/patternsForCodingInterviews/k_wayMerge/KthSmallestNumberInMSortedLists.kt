package patternsForCodingInterviews.k_wayMerge

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744417564_109Unit
object KthSmallestNumberInMSortedLists {
    @JvmStatic
    fun main(args: Array<String>) {
        val l1 = arrayOf(2, 6, 8)
        val l2 = arrayOf(3, 6, 7)
        val l3 = arrayOf(1, 3, 4)
        val lists = LinkedList<Array<Int>?>()
        lists.add(l1)
        lists.add(l2)
        lists.add(l3)
        val result = findKthSmallest(lists, 5)
        print("Kth smallest number is: $result")
    }

    /*
    Time: O(K * log M) k elements among all the arrays, M total number of input arrays.
    Space: O(M) because at any time, our min-heap will be storing one number from all M input arrays.
     */
    fun findKthSmallest(lists: LinkedList<Array<Int>?>, k: Int): Int {
        val minHeap = PriorityQueue { n1: Node, n2: Node ->
            lists[n1.arrayIndex]!![n1.elementIndex] -
                    lists[n2.arrayIndex]!![n2.elementIndex]
        }
        // put the 1st element of each array in the min heap
        for (i in lists.indices) {
            if (lists[i] != null) {
                minHeap.add(Node(0, i))
            }
        }
        // take the smallest (top) element form the min heap, if the running count is equal
        // to k return the number if the array of the top element has more elements, add the
        // next element to the heap
        var numberCount = 0
        var result = 0
        while (!minHeap.isEmpty()) {
            val node = minHeap.poll()
            result = lists[node.arrayIndex]!![node.elementIndex]
            if (++numberCount == k) {
                break
            }
            node.elementIndex++
            if (lists[node.arrayIndex]!!.size > node.elementIndex) {
                minHeap.add(node)
            }
        }
        return result
    }

    internal class Node(var elementIndex: Int, var arrayIndex: Int)
}