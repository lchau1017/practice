package patternsForCodingInterviews.inPlaceReversalOfALinkedList

object ReverseALinkedList {
    @JvmStatic
    fun main(args: Array<String>) {
        val head = ListNode(2)
        head.next = ListNode(4)
        head.next!!.next = ListNode(6)
        head.next!!.next!!.next = ListNode(8)
        head.next!!.next!!.next!!.next = ListNode(10)
        var result = reverse(head)
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
    fun reverse(head: ListNode?): ListNode? {
        var current = head
        var previous: ListNode? = null
        var next: ListNode? = null
        while (current != null) {
            next = current.next
            current.next = previous
            previous = current
            current = next
        }
        return previous
    }
}