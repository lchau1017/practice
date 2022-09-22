package patternsForCodingInterviews.inPlaceReversalOfALinkedList

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743804225_41Unit
object _1ReverseAlternatingK_elementSun_List {
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
        var result = reverse(head, 2)
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
    fun reverse(head: ListNode?, k: Int): ListNode? {
        var head = head
        if (k <= 1 || head == null) {
            return head
        }
        var current = head
        var previous: ListNode? = null
        while (current != null) { //Break if we reach the end of the list
            val lastNodeOfPreviousPart = previous
            //after reversing the list 'current' will become the last node of the sub-list
            val lastNodeOfSubList: ListNode = current
            var next: ListNode? = null //temporarily store the next node
            //reverse 'k' nodes
            run {
                var i = 0
                while (current != null && i < k) {
                    next = current!!.next
                    current!!.next = previous
                    previous = current
                    current = next
                    i++
                }
            }
            //connect with the previous part
            if (lastNodeOfPreviousPart != null) {
                lastNodeOfPreviousPart.next = previous
            } else { //we are changing the first node (head) of the linkedList
                head = previous
            }
            //connect with the next part
            lastNodeOfSubList.next = current
            //skip 'k' nodes
            var i = 0
            while (current != null && i < k) {
                previous = current
                current = current!!.next
                i++
            }
        }
        return head
    }
}