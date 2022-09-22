package patternsForCodingInterviews.k_wayMerge

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744411540_108Unit
object MergeKSortedLists {
    @JvmStatic
    fun main(args: Array<String>) {
        val l1 = ListeNode(2)
        l1.next = ListeNode(6)
        l1.next!!.next = ListeNode(8)
        val l2 = ListeNode(3)
        l2.next = ListeNode(6)
        l2.next!!.next = ListeNode(7)
        val l3 = ListeNode(1)
        l3.next = ListeNode(3)
        l3.next!!.next = ListeNode(4)
        var result = merge(arrayOf(l1, l2, l3))
        print("Here are the elements form the merged list: ")
        while (result != null) {
            print(result.value.toString() + " ")
            result = result.next
        }
    }

    /*
    Time: 0(N * logK) we remove and add one element to the heap in each step
    N: total number of elements, K input lists
    Space: O(K) store one number from all K input lists
     */
    fun merge(lists: Array<ListeNode>): ListeNode? {
        //get the smaller value in priority
        val minheap = PriorityQueue { n1: ListeNode?, n2: ListeNode? -> n1!!.value - n2!!.value }
        // put the root of each list in the min heap
        for (root in lists) {
            minheap.add(root)
        }
        // take the smallest (top) element form the min-heap and add it to the result;
        // if the top element has a next element add it to the heap
        var resultHead: ListeNode? = null
        var resultTail: ListeNode? = null
        while (!minheap.isEmpty()) {
            val node = minheap.poll()
            if (resultHead == null) {
                resultTail = node
                resultHead = resultTail
            } else {
                resultTail!!.next = node
                resultTail = resultTail.next
            }
            if (node!!.next != null) {
                minheap.add(node.next)
            }
        }
        return resultHead
    }
}