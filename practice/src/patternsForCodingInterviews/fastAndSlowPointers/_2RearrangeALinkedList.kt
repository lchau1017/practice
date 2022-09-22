package patternsForCodingInterviews.fastAndSlowPointers

object _2RearrangeALinkedList {
    @JvmStatic
    fun main(args: Array<String>) {
        var head: ListNode? = ListNode(2)
        head!!.next = ListNode(4)
        head.next!!.next = ListNode(6)
        head.next!!.next!!.next = ListNode(8)
        head.next!!.next!!.next!!.next = ListNode(10)
        head.next!!.next!!.next!!.next!!.next = ListNode(12)
        reorder(head)
        while (head != null) {
            print(head.value.toString() + " ")
            head = head.next
        }
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    fun reorder(head: ListNode?) {
        if (head == null || head.next == null) return

        // find the middle of the LinkedList
        var slow = head
        var fast = head
        while (fast != null && fast.next != null) {
            slow = slow!!.next
            fast = fast.next!!.next
        }

        // slow is now pointing to the middle node
        var headSecondHalf = reverse(slow) // reverse the second half
        var headFirstHalf = head

        // rearrange to produce the LinkedList in the required order
        while (headFirstHalf != null && headSecondHalf != null) {
            var temp = headFirstHalf.next
            headFirstHalf.next = headSecondHalf
            headFirstHalf = temp
            temp = headSecondHalf.next
            headSecondHalf.next = headFirstHalf
            headSecondHalf = temp
        }

        // set the next of the last node to 'null'
        if (headFirstHalf != null) headFirstHalf.next = null
    }

    private fun reverse(slow: ListNode?): ListNode? {
        var slow = slow
        var prev: ListNode? = null
        while (slow != null) {
            val next = slow.next
            slow.next = prev
            prev = slow
            slow = next
        }
        return prev
    }
}