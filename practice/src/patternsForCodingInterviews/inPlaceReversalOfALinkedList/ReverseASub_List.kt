package patternsForCodingInterviews.inPlaceReversalOfALinkedList

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743790633_39Unit
object ReverseASub_List {
    @JvmStatic
    fun main(args: Array<String>) {
        val head = ListNode(1)
        head.next = ListNode(2)
        head.next!!.next = ListNode(3)
        head.next!!.next!!.next = ListNode(4)
        head.next!!.next!!.next!!.next = ListNode(5)
        var result = reverse(head, 2, 4)
        print("Nodes of the reversed LinkedList are: ")
        while (result != null) {
            print(result.value.toString() + " ")
            result = result.next
        }
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    fun reverse(head: ListNode?, p: Int, q: Int): ListNode? {
        var head = head
        if (p == q) return head

        // after skipping 'p-1' nodes, current will point to 'p'th node
        var current = head
        var previous: ListNode? = null
        run {
            var i = 0
            while (current != null && i < p - 1) {
                previous = current
                current = current!!.next
                ++i
            }
        }

        // we are interested in three parts of the LinkedList, part before index 'p', part
        // between 'p' and 'q', and the part after index 'q'
        val lastNodeOfFirstPart = previous // points to the node at index 'p-1'
        // after reversing the LinkedList 'current' will become the last node of the sub-list
        val lastNodeOfSubList = current
        var next: ListNode? = null // will be used to temporarily store the next node
        // reverse nodes between 'p' and 'q'
        var i = 0
        while (current != null && i < q - p + 1) {
            next = current!!.next
            current!!.next = previous
            previous = current
            current = next
            i++
        }

        // connect with the first part
        if (lastNodeOfFirstPart != null) // 'previous' is now the first node of the sub-list
            lastNodeOfFirstPart.next =
                previous else  // this means p == 1 i.e., we are changing the first node (head) of the LinkedList
            head = previous

        // connect with the last part
        lastNodeOfSubList!!.next = current
        return head
    }
}