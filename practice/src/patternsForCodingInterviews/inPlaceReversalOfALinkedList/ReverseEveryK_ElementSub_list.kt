package patternsForCodingInterviews.inPlaceReversalOfALinkedList

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743796880_40Unit
object ReverseEveryK_ElementSub_list {
    @JvmStatic
    fun main(args: Array<String>) {
        val head = ListNode(1)
        head.next = ListNode(2)
        head.next!!.next = ListNode(3)
        head.next!!.next!!.next = ListNode(4)
        head.next!!.next!!.next!!.next = ListNode(5)
        head.next!!.next!!.next!!.next!!.next = ListNode(6)
        head.next!!.next!!.next!!.next!!.next!!.next = ListNode(7)
        head.next!!.next!!.next!!.next!!.next!!.next!!.next = ListNode(8)
        var result = reverse(head, 3)
        print("Nodes of the reversed LinkedList are: ")
        while (result != null) {
            print(result.value.toString() + " ")
            result = result.next
        }
    }

    fun reverse(head: ListNode?, k: Int): ListNode? {
        var head = head
        if (k <= 1 || head == null) {
            return head
        }
        var current = head
        var previous: ListNode? = null
        while (true) {
            val lastNodeOfPreviousPart = previous
            // after reversing the List 'current' will become the last node of the sub-list
            val latNodeOfSubList = current
            //used to temporarily store the next node
            var next: ListNode? = null
            //reverse 'k' nodes
            var i = 0
            while (current != null && i < k) {
                next = current.next
                current.next = previous
                previous = current
                current = next
                i++
            }
            // connect with the previous part
            if (lastNodeOfPreviousPart != null) {
                // 'previous' is now the first node of the sub-list
                lastNodeOfPreviousPart.next = previous
            } else { // this means we are changing the first node (head) of the LinkedList
                head = previous
            }
            // connect with the next part
            latNodeOfSubList!!.next = current
            if (current == null) { //break, we have reached the end of the LinkedList
                break
            }
            //prepare for the next sub-list
            previous = latNodeOfSubList
        }
        return head
    }
}